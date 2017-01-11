/**
 * @author nrapopor - Nick Rapoport
 * @copyright Copyright 2017 ( Jan 9, 2017 ) Nick Rapoport all rights reserved.
 */
package com.parasoft.sa.ctp;

import java.util.Map;

import org.apache.commons.lang3.text.WordUtils;

/**
 * <DL>
 * <DT>Description:</DT>
 * <DD>This class is used to manage the current rules for Syllables as they are assembled into the name</DD>
 * <DT>Date:</DT>
 * <DD>Jan 9, 2017</DD>
 * </DL>
 *
 * @author nrapopor - Nick Rapoport
 *
 */
public class SyllableRulesManager implements ISyllableRulesManager {
    private static final org.slf4j.Logger LOG = org.slf4j.LoggerFactory.getLogger(SyllableRulesManager.class);

    private int expecting;

    private final int syllableCount;

    private ISyllable lastSyllable;

    Map<SyllableType, ISyllableListHolder> allSyllables;

    /**
     * <DL>
     * <DT>Description:</DT>
     * <DD>SyllableRulesManager Constructor</DD>
     * <DT>Date:</DT>
     * <DD>Jan 9, 2017</DD>
     * </DL>
     *
     * @param aSyllableCount
     *            number of syllable for the name to be created
     * @param aAllSyllables
     *            the map that contains all the Syllable lists keyed by SyllableType
     */
    public SyllableRulesManager(final int aSyllableCount, final Map<SyllableType, ISyllableListHolder> aAllSyllables) {
        super();
        expecting = 0;
        syllableCount = aSyllableCount;
        allSyllables = aAllSyllables;
    }

    /**
     * <DL>
     * <DT>Description:</DT>
     * <DD>process middle (root) syllables. There may be more than one (syllable count > 3 )</DD>
     * <DT>Date:</DT>
     * <DD>Jan 10, 2017</DD>
     * </DL>
     *
     * @param repeatSyllableAllowed
     *            <DL>
     *            <DT><code>true</code></DT>
     *            <DD>the same middle syllable is allowed to be repeated in the name.</DD>
     *            <DT><code>false</code></DT>
     *            <DD>otherwise</DD>
     *            </DL>
     * @return
     * @throws NameGeneratorException
     */
    protected StringBuilder addMiddleToName(final StringBuilder aName, final boolean repeatSyllableAllowed)
            throws NameGeneratorException {
        final StringBuilder name = aName == null ? new StringBuilder() : aName;
        final ISyllable randomPrefix = getLastSyllable();
        final ISyllableListHolder middleList = getMiddle();
        if (isMiddlePartExpected()) {
            setExpecting(middleList.determineExpectation(randomPrefix));
            middleList.determineAllowed(randomPrefix);
        } else {
            return name;
        }

        for (int i = 0; i < middleSyllableCount(); i++) {
            ISyllable previousMid = null;
            ISyllable randomMid = null;
            do {
                randomMid = middleList.randomSyllable();
                if (LOG.isTraceEnabled()) {
                    LOG.trace(LOG_EXPECTATION_STATE, getExpectingString(), randomMid, getLastLetterTypeString(),
                        notMatchedWithExpectations(randomMid) ? "does" : "does not"); //$NON-NLS-1$ //$NON-NLS-2$
                }

            } while (notValidAgainstPrevious(randomMid) || !repeatSyllableAllowed && randomMid == previousMid);

            name.append(randomMid.getPure());
            previousMid = randomMid;
            setExpecting(0);
            setLastSyllable(randomMid);
            if (i < indexOflastMiddleSyllable()) {
                middleList.determineAllowed(randomMid);
                setExpecting(middleList.determineExpectation(randomMid));
            }
        }

        return name;
    }

    /**
     * <DL>
     * <DT>Description:</DT>
     * <DD>initialize the name with the random prefix. if the passed StringBuilder is null , create one</DD>
     * <DT>Date:</DT>
     * <DD>Jan 10, 2017</DD>
     * </DL>
     *
     * @param aName
     *            a StringBuilder to which the prefix is to be added
     * @return StringBuilder containing the prefix
     */
    protected StringBuilder addPrefixToName(final StringBuilder aName) {
        final StringBuilder name = aName == null ? new StringBuilder() : aName;
        final ISyllable prefix = getPrefix().randomSyllable();
        setLastSyllable(prefix);
        name.append(WordUtils.capitalizeFully(prefix.getPure()));
        return name;
    }

    /**
     * <DL>
     * <DT>Description:</DT>
     * <DD>Add the suffix to the passed StringBuffer (holding the name). if the passed buffer is null create one.</DD>
     * <DT>Date:</DT>
     * <DD>Jan 11, 2017</DD>
     * </DL>
     *
     * @param aName
     * @return the passed buffer with the random, rule compliant suffix added
     * @throws NameGeneratorException
     */
    protected StringBuilder addSuffixToName(final StringBuilder aName) throws NameGeneratorException {
        final StringBuilder name = aName == null ? new StringBuilder() : aName;
        if (getSyllableCount() > 1) {
            ISyllable randomSuffix;
            final ISyllableListHolder suffixList = getSuffix();
            final ISyllable last = getLastSyllable();
            suffixList.determineAllowed(last);
            setExpecting(suffixList.determineExpectation(last));
            do {
                randomSuffix = suffixList.randomSyllable();
            } while (notValidAgainstPrevious(randomSuffix));
            name.append(randomSuffix.getPure());
        }
        return name;
    }

    /** {@inheritDoc} */
    @Override
    public StringBuilder constructName(final boolean repeatSyllableAllowed) throws NameGeneratorException {
        StringBuilder name = addPrefixToName(new StringBuilder());
        name = addMiddleToName(name, repeatSyllableAllowed);
        name = addSuffixToName(name);
        return name;
    }

    /**
     * <DL>
     * <DT>Description:</DT>
     * <DD>Getter for the allSyllables property</DD>
     * <DT>Date:</DT>
     * <DD>Jan 10, 2017</DD>
     * </DL>
     *
     * @return the value of allSyllables field
     */
    protected Map<SyllableType, ISyllableListHolder> getAllSyllables() {
        return allSyllables;
    }

    /**
     * <DL>
     * <DT>Description:</DT>
     * <DD>Getter for the expecting property</DD>
     * <DT>Date:</DT>
     * <DD>Jan 9, 2017</DD>
     * </DL>
     *
     * @return the value of expecting field
     */
    protected String getExpectingString() {
        return isExpectingVowel() ? IConstants.VOWEL : IConstants.CONSONANT;
    }

    /**
     * <DL>
     * <DT>Description:</DT>
     * <DD>Getter for the last property</DD>
     * <DT>Date:</DT>
     * <DD>Jan 9, 2017</DD>
     * </DL>
     *
     * @return the value of last field
     */
    protected String getLastLetterTypeString() {
        return getLastSyllable().isEndsWithVowel() ? IConstants.VOWEL : IConstants.CONSONANT;
    }

    /**
     * <DL>
     * <DT>Description:</DT>
     * <DD>Getter for the lastSyllable property</DD>
     * <DT>Date:</DT>
     * <DD>Jan 9, 2017</DD>
     * </DL>
     *
     * @return the value of lastSyllable field
     */
    public ISyllable getLastSyllable() {
        return lastSyllable;
    }

    /** {@inheritDoc} */
    @Override
    public ISyllableListHolder getMiddle() {
        return getAllSyllables().get(SyllableType.MIDDLE);

    }

    /** {@inheritDoc} */
    @Override
    public ISyllableListHolder getPrefix() {
        return getAllSyllables().get(SyllableType.PREFIX);

    }

    /** {@inheritDoc} */
    @Override
    public ISyllableListHolder getSuffix() {
        return getAllSyllables().get(SyllableType.SUFFIX);

    }

    /** {@inheritDoc} */
    @Override
    public int getSyllableCount() {
        return syllableCount;
    }

    /**
     * <DL>
     * <DT>Description:</DT>
     * <DD>the index of the last middle syllable to be added to the name. this is used to determine what list should be
     * used for the next expectations</DD>
     * <DT>Date:</DT>
     * <DD>Jan 11, 2017</DD>
     * </DL>
     *
     * @return the index of the last middle syllable
     */
    protected int indexOflastMiddleSyllable() {
        return getSyllableCount() - 3;
    }

    /**
     * <DL>
     * <DT>Description:</DT>
     * <DD>is the next syllable MUST start with the consonant</DD>
     * <DT>Date:</DT>
     * <DD>Jan 11, 2017</DD>
     * </DL>
     *
     * @return
     *         <DL>
     *         <DT><code>true</code></DT>
     *         <DD>Next syllable must start with the consonant</DD>
     *         <DT><code>false</code></DT>
     *         <DD>otherwise</DD>
     *         </DL>
     */
    protected boolean isExpectingConsonant() {
        return expecting == 2;
    }

    /**
     * <DL>
     * <DT>Description:</DT>
     * <DD>is the next syllable MUST start with the vowel</DD>
     * <DT>Date:</DT>
     * <DD>Jan 11, 2017</DD>
     * </DL>
     *
     * @return
     *         <DL>
     *         <DT><code>true</code></DT>
     *         <DD>Next syllable must start with the vowel</DD>
     *         <DT><code>false</code></DT>
     *         <DD>otherwise</DD>
     *         </DL>
     */
    protected boolean isExpectingVowel() {
        return expecting == 1;
    }

    /**
     * <DL>
     * <DT>Description:</DT>
     * <DD>Determines if there are more than two syllable in the name</DD>
     * <DT>Date:</DT>
     * <DD>Jan 11, 2017</DD>
     * </DL>
     *
     * @return
     *         <DL>
     *         <DT><code>true</code></DT>
     *         <DD>if there are more than two syllables in the name (prefix & suffix)</DD>
     *         <DT><code>false</code></DT>
     *         <DD>otherwise</DD>
     *         </DL>
     */
    protected boolean isMiddlePartExpected() {
        return getSyllableCount() > 2;
    }

    /**
     * <DL>
     * <DT>Description:</DT>
     * <DD>returns the number of middle syllable expected</DD>
     * <DT>Date:</DT>
     * <DD>Jan 11, 2017</DD>
     * </DL>
     *
     * @return number of middle syllable
     */
    protected int middleSyllableCount() {
        return getSyllableCount() - 2;
    }

    /**
     * <DL>
     * <DT>Description:</DT>
     * <DD>Validates the passed syllable against expectation based on the common rules</DD>
     * <DT>Date:</DT>
     * <DD>Jan 10, 2017</DD>
     * </DL>
     *
     * @param newSyllable
     * @return
     *         <DL>
     *         <DT><code>true</code></DT>
     *         <DD>the syllable does NOT meet expectations</DD>
     *         <DT><code>false</code></DT>
     *         <DD>otherwise</DD>
     *         </DL>
     */
    protected boolean notMatchedWithExpectations(final ISyllable newSyllable) {
        return isExpectingVowel() && !newSyllable.isStartsWithVowel()
            || isExpectingConsonant() && !newSyllable.isStartsWithConsonant();
    }

    /**
     * <DL>
     * <DT>Description:</DT>
     * <DD>Validates the passed syllable against previous syllable</DD>
     * <DT>Date:</DT>
     * <DD>Jan 10, 2017</DD>
     * </DL>
     *
     * @param newSyllable
     * @return
     *         <DL>
     *         <DT><code>true</code></DT>
     *         <DD>the syllable does NOT match with previous syllable</DD>
     *         <DT><code>false</code></DT>
     *         <DD>otherwise</DD>
     *         </DL>
     */
    protected boolean notMatchedWithPreviousSyllable(final ISyllable newSyllable) {
        return getLastSyllable().isEndsWithVowel() && newSyllable.isHatesPreviousVowels()
            || getLastSyllable().isEndsWithConsonant() && newSyllable.isHatesPreviousCons();
    }

    /**
     * <DL>
     * <DT>Description:</DT>
     * <DD>Validates the passed syllable against previous syllable and the expectation based on the common rules</DD>
     * <DT>Date:</DT>
     * <DD>Jan 10, 2017</DD>
     * </DL>
     *
     * @param newSyllable
     * @return
     *         <DL>
     *         <DT><code>true</code></DT>
     *         <DD>the syllable does NOT meet expectations or is mismatched with the previous syllable</DD>
     *         <DT><code>false</code></DT>
     *         <DD>otherwise</DD>
     *         </DL>
     */
    protected boolean notValidAgainstPrevious(final ISyllable newSyllable) {
        return notMatchedWithPreviousSyllable(newSyllable) || notMatchedWithExpectations(newSyllable);

    }

    /**
     * <DL>
     * <DT>Description:</DT>
     * <DD>Setter for the expecting property</DD>
     * <DT>Date:</DT>
     * <DD>Jan 9, 2017</DD>
     * </DL>
     *
     * @param aExpecting
     *            new value for the expecting property
     */
    protected void setExpecting(final int aExpecting) {
        expecting = aExpecting;
    }

    /**
     * <DL>
     * <DT>Description:</DT>
     * <DD>Setter for the lastSyllable property</DD>
     * <DT>Date:</DT>
     * <DD>Jan 9, 2017</DD>
     * </DL>
     *
     * @param aLastSyllable
     *            new value for the lastSyllable property
     */
    protected void setLastSyllable(final ISyllable aLastSyllable) {
        lastSyllable = aLastSyllable;
    }

    /** {@inheritDoc} */
    @Override
    public void validate(final String filename) throws NameGeneratorException {
        if (!getPrefix().isListvalid()) {
            throw new NameGeneratorException(String.format(MISSING_PREFIX_ERROR_FMT, filename));
        }
        if (!getSuffix().isListvalid()) {
            throw new NameGeneratorException(String.format(MISSING_SUFFIX_ERROR_FMT, filename));
        }
        if (getSyllableCount() < 1) {
            throw new NameGeneratorException(MIN_SYLLABLES_ERROR);
        }
        if (isMiddlePartExpected() && !getMiddle().isListvalid()) {
            throw new NameGeneratorException(String.format(MISSING_MIDDLE_ERROR_FMT, filename));
        }

    }

}
