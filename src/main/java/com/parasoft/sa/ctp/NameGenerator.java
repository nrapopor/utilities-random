/**
 * @author nrapopor - Nick Rapoport
 * @copyright Copyright 2016 ( Dec 25, 2016 ) Nick Rapoport all rights reserved.
 */
package com.parasoft.sa.ctp;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * <DL>
 * <DT>License</DT>
 * <DD>
 * <P>
 * This class is released under GNU general public license</DD>
 * <DT>Short Description:
 * <DT>
 * <DD>This class generates random names from syllables, and provides programmer a simple way to set a group of rules
 * for generator to avoid unpronounceable and bizarre names.</DD>
 * <DT>Details</DT>
 * <DD>
 * <DL>
 * <DT>Syllable file:</DT>
 * <DD>
 * <OL>
 * <LI>Will be searched for in the classpath</LI>
 * <LI>Can be passed into the constructor</LI>
 * <LI>Can be passed in the system property <code>com.parasoft.sa.ctp.syllable.file</code></LI>
 * <LI>Name will be defaulted to <code>NameGeneratorSyllable.txt</code> if not passed through the constructor and the
 * system property is not set</LI>
 * </OL>
 * </DD>
 * <DT>Syllable file Requirements/Format:</DT>
 * <DD>
 * <OL>
 * <LI>All syllables are separated by line break.
 * <LI>Syllable should not contain or start with whitespace, as this character is ignored and only first part of the
 * syllable is read.
 * <LI>+ and - characters are used to set rules, and using them in other way, may result in unpredictable results.
 * <LI>Empty lines are ignored. Also lines that start with <code>#</code>
 * </OL>
 * </DD>
 *
 * <DT>Syllable Classification:</DT>
 * <DD>Name is usually composed from 3 different class of syllables, which include prefix, middle part and suffix. To
 * declare syllable as a prefix in the file, insert "-" as a first character of the line. To declare syllable as a
 * suffix in the file, insert "+" as a first character of the line. everything else is read as a middle part.</DD>
 *
 * <DT>Number Of Syllables:</DT>
 * <DD>Names may have any positive number of syllables. In case of 2 syllables, name will be composed from prefix and
 * suffix. In case of 1 syllable, name will be chosen from amongst the prefixes. In case of 3 and more syllables, name
 * will begin with prefix, is filled with middle parts and ended with suffix.</DD>
 * <DT>Assigning Rules:</DT>
 * <DD>I included a way to set 4 kind of rules for every syllable. To add rules to the syllables, write them right after
 * the syllable and SEPARATE WITH WHITESPACE. (example: <code>"aad +v -c"</code>). The order of rules is not
 * important.</DD>
 *
 * <DT>Rules:</DT>
 * <DD>
 * <OL>
 * <LI>+v means that next syllable must definitely start with a vowel.</LI>
 * <LI>+c means that next syllable must definitely start with a consonant.</LI>
 * <LI>-v means that this syllable can only be added to another syllable, that ends with a vowel.</LI>
 * <LI>-c means that this syllable can only be added to another syllable, that ends with a consonant. So, our example:
 * "aad +v -c" means that "aad" can only be after consonant and next syllable must start with vowel. Beware of creating
 * logical mistakes, like providing only syllables ending with consonants, but expecting only vowels, which will be
 * detected and <code>NameGeneratorException</code> will be thrown.</LI>
 * </OL>
 * </DD>
 *
 * <DT>To Start:</DT>
 * <DD>Create a new <code>NameGenerator</code> object, provide the syllable file (through one of the methods describe
 * above), and create names using <code>compose()</code> method.</DD>
 * </DL>
 * </DD>
 * </DL>
 *
 * <DL>
 * <DT>Modifications
 * <DT>
 * <DD>
 * <DL>
 * <DT>Nick Rapoport:</DT>
 * <DD>
 * <OL>
 * <LI>Replaced the 'vocal' with 'vowel'. As vowels what was obviously was meant here: (a, e, i, o, u, y).</LI>
 * <LI>Switched to English only (for now).</LI>
 * <LI>Switched to loading the files from classpath and provided a default syllable file name to use</LI>
 * <LI>added an environment variable to override the default syllable file name</LI>
 * <LI>Cleaned-up the javadoc comments for readability and added descriptions of new functionality</LI>
 * <LI>Re-factored the code for better encapsulation and readability</LI>
 * <LI>Added logging support for running in the Parasoft's Virtualize server context</LI>
 * <LI>Added jUnit tests to validate the functionality</LI>
 * <LI>Added a property to allow/deny repeated middle syllables</LI>
 * </OL>
 * </DD>
 * </DL>
 * </DD>
 * </DL>
 *
 * @author Joonas Vali, August 2009.
 * @author (Modifications) Nick Rapoport, December 2016
 *
 *
 */
public class NameGenerator implements INameGenerator {

    private static final org.slf4j.Logger LOG = org.slf4j.LoggerFactory.getLogger(NameGenerator.class);

    final Map<SyllableType, ISyllableListHolder> parts = Collections.synchronizedMap(new LinkedHashMap<>());

    private String fileName;

    private boolean repeatSyllable;

    /**
     * <DL>
     * <DT>Description:</DT>
     * <DD>NameGenerator Constructor: Create new random name generator object. The file name will determined from System
     * property or default, refresh() is automatically called.
     *
     * </DD>
     * <DT>Date:</DT>
     * <DD>Dec 25, 2016</DD>
     * </DL>
     *
     * @throws NameGeneratorException
     */
    public NameGenerator() throws NameGeneratorException {
        this(System.getProperty(SYLLABLE_FILE, SYLLABLE_FILE_DEFAULT));
    }

    /**
     * <DL>
     * <DT>Description:</DT>
     * <DD>NameGenerator Constructor: Create new random name generator object. refresh() is automatically called.
     *
     * </DD>
     * <DT>Date:</DT>
     * <DD>Dec 25, 2016</DD>
     * </DL>
     *
     * @param fileNameIn
     *            file name of a classpath resource, where syllables are located
     * @throws NameGeneratorException
     */
    public NameGenerator(final String fileNameIn) throws NameGeneratorException {
        fileName = fileNameIn;
        parts.put(SyllableType.PREFIX, new SyllableListHolder());
        parts.put(SyllableType.MIDDLE, new SyllableListHolder());
        parts.put(SyllableType.SUFFIX, new SyllableListHolder());
        refresh();
    }

    /** {@inheritDoc} */
    @Override
    public void changeFile(final String fileName) throws NameGeneratorException {
        if (fileName == null || fileName.isEmpty()) {
            throw new NameGeneratorException(FILE_NAME_ERROR);
        }
        setFileName(fileName);
        refresh();
    }

    /** {@inheritDoc} */
    @Override
    public String compose(final int syls) throws NameGeneratorException {
        final ISyllableRulesManager manager = new SyllableRulesManager(syls, getParts());
        manager.validate(getFileName());
        final StringBuilder name = manager.constructName(isRepeatSyllable());
        return name.toString();

    }

    /** {@inheritDoc} */
    @Override
    public String getFileName() {
        return fileName;
    }

    /** {@inheritDoc} */
    @Override
    public Map<SyllableType, ISyllableListHolder> getParts() {
        return parts;
    }

    /** {@inheritDoc} */
    @Override
    public boolean isRepeatSyllable() {
        return repeatSyllable;
    }

    /** {@inheritDoc} */
    @Override
    public void refresh() throws NameGeneratorException {
        setRepeatSyllable(Boolean.parseBoolean(System.getProperty(SYLLABLE_REPEAT, DEFAULT_SYLLABLE_REPEAT)));
        Utils.getInstance().refresh(getFileName(), getParts(), LOG);
    }

    /** {@inheritDoc} */
    @Override
    public void setFileName(final String aFileName) {
        fileName = aFileName;
    }

    /** {@inheritDoc} */
    @Override
    public void setRepeatSyllable(final boolean aRepeatSyllable) {
        repeatSyllable = aRepeatSyllable;
    }

}
