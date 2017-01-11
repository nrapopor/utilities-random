/**
 * @author nrapopor - Nick Rapoport
 * @copyright Copyright 2017 ( Jan 7, 2017 ) Nick Rapoport all rights reserved.
 */
package com.parasoft.sa.ctp;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * <DL>
 * <DT>Description:</DT>
 * <DD>Test SyllableListHolder class</DD>
 * <DT>Date:</DT>
 * <DD>Jan 7, 2017</DD>
 * </DL>
 *
 * @author nrapopor - Nick Rapoport
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SyllableListHolderTest implements IConstants {
    private static org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(SyllableListHolderTest.class);

    /**
     * <DL>
     * <DT>Description:</DT>
     * <DD>Set Up Before Class/DD>
     * <DT>Date:</DT>
     * <DD>Jan 7, 2017</DD>
     * </DL>
     *
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    /**
     * <DL>
     * <DT>Description:</DT>
     * <DD>Tear Down After Class</DD>
     * <DT>Date:</DT>
     * <DD>Jan 7, 2017</DD>
     * </DL>
     *
     * @throws java.lang.Exception
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    /** {@inheritDoc} */
    public void changeFile(final String fileName) throws NameGeneratorException {
    }

    /**
     * <DL>
     * <DT>Description:</DT>
     * <DD>process the determineAllowedTest method and assert the results are as expected</DD>
     * <DT>Date:</DT>
     * <DD>Jan 7, 2017</DD>
     * </DL>
     *
     * @param l_FileName
     * @param leterType
     * @param expectedKey
     * @param testSyllable
     */
    private void determineAllowed(final String l_FileName, final String leterType, final String expectedKey,
        final ISyllable testSyllable) {
        final boolean thrown = determineAllowedTest(l_FileName, testSyllable, leterType, expectedKey);
        assertTrue("exception was expected", thrown); //$NON-NLS-1$
    }

    /**
     * <DL>
     * <DT>Description:</DT>
     * <DD>use the passed arguments to load the SyllableListHolder and validate the allowed state for the passed
     * Syllable</DD>
     * <DT>Date:</DT>
     * <DD>Jan 7, 2017</DD>
     * </DL>
     *
     * @param l_FileName
     *            the file to load the SyllableListHolder from
     * @param testSyllable
     *            the syllable to use for the determination
     * @param leterType
     *            Vowel or Consonant
     * @param expectedKey
     *            -v / -c
     * @param thrown
     *            <DL>
     *            <DT><code>true</code></DT>
     *            <DD>An exception was thrown by the invocation and it was the expected one</DD>
     *            <DT><code>false</code></DT>
     *            <DD>No exception was thrown (this may be a test failure since the exception may have been expected)
     *            </DD>
     *            </DL>
     * @return
     */
    private boolean determineAllowedTest(final String l_FileName, final ISyllable testSyllable, final String leterType,
        final String expectedKey) {
        boolean thrown = false;

        final ISyllableListHolder test = new SyllableListHolder();
        assertNotNull(test);
        try {
            test.loadListFromFile(l_FileName, SyllableType.MIDDLE);
            assertTrue("Nothing got loaded, check the file contains middle syllables (no +/- as first char)", //$NON-NLS-1$
                test.size() > 0);
        } catch (final NameGeneratorException ex) {
            log.error("caught {} Error : ", ex.getClass().getSimpleName() //$NON-NLS-1$
                , ex);
            fail("Unexpected exception"); //$NON-NLS-1$
        }
        final String expected = String.format(IConstants.ALLOWED_ERROR_FMT, test.getType().toString().toLowerCase(),
            leterType, testSyllable.toString(), expectedKey);
        try {
            test.determineAllowed(testSyllable);

        } catch (final NameGeneratorException ex) {
            log.info("caught expected {} : {} ", ex.getClass().getSimpleName() //$NON-NLS-1$
                , ex.getMessage());
            assertEquals("Expected no middle " + leterType + " message", expected.trim(), ex.getMessage().trim()); //$NON-NLS-1$ //$NON-NLS-2$
            thrown = true;
        }
        return thrown;
    }

    /**
     * <DL>
     * <DT>Description:</DT>
     * <DD>Assert the passed SyllableListHolder is loaded from the passed List</DD>
     * <DT>Date:</DT>
     * <DD>Jan 7, 2017</DD>
     * </DL>
     *
     * @param test
     *            SyllableListHolder to validate
     * @param newList
     *            the basis for validation
     */
    private void doAsserts(final ISyllableListHolder test, final List<Syllable> newList) {
        assertEquals(test.size(), newList.size());
        assertTrue(test.contains(newList.get(0)));
        assertEquals(test.get(0), newList.get(0));
        assertEquals(newList.get(0).getType(), test.getType());
    }

    /**
     * <DL>
     * <DT>Description:</DT>
     * <DD>Set Up before each test</DD>
     * <DT>Date:</DT>
     * <DD>Jan 7, 2017</DD>
     * </DL>
     *
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
    }

    /**
     * <DL>
     * <DT>Description:</DT>
     * <DD>Tear Down After each test</DD>
     * <DT>Date:</DT>
     * <DD>Jan 7, 2017</DD>
     * </DL>
     *
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test method for
     * {@link com.parasoft.sa.ctp.SyllableListHolder#determineExpectation(com.parasoft.sa.ctp.Syllable)}.
     */
    @Test
    public void testAllowed() {
        //final String l_FileName = "/NameGeneratorSyllable-ConsNoPrevConsonants.txt";
        // reset all the Syllable Lists to the initial state
        determineAllowed("/NameGeneratorSyllable-ConsNoPrevConsonants.txt", IConstants.CONSONANT, PREVIOUS_CONSONANT, //$NON-NLS-1$
            new Syllable("-xx +c")); //$NON-NLS-1$
        determineAllowed("/NameGeneratorSyllable-ConsNoPrevConsonants.txt", IConstants.CONSONANT, PREVIOUS_CONSONANT, //$NON-NLS-1$
            new Syllable("-xx +v")); //$NON-NLS-1$
        determineAllowed("/NameGeneratorSyllable-ConsNoPrevVowels.txt", IConstants.VOWEL, PREVIOUS_VOWEL, //$NON-NLS-1$
            new Syllable("-xa +c")); //$NON-NLS-1$
        determineAllowed("/NameGeneratorSyllable-ConsNoPrevVowels.txt", IConstants.VOWEL, PREVIOUS_VOWEL, //$NON-NLS-1$
            new Syllable("-xa +v")); //$NON-NLS-1$
        determineAllowed("/NameGeneratorSyllable-VowelsNoPrevConsonants.txt", IConstants.CONSONANT, PREVIOUS_CONSONANT, //$NON-NLS-1$
            new Syllable("-xx +c")); //$NON-NLS-1$
        determineAllowed("/NameGeneratorSyllable-VowelsNoPrevConsonants.txt", IConstants.CONSONANT, PREVIOUS_CONSONANT, //$NON-NLS-1$
            new Syllable("-xx +v")); //$NON-NLS-1$
        determineAllowed("/NameGeneratorSyllable-VowelsNoPrevVowels.txt", IConstants.VOWEL, PREVIOUS_VOWEL, //$NON-NLS-1$
            new Syllable("-xa +v")); //$NON-NLS-1$
        ((org.apache.logging.log4j.core.Logger) LogManager.getLogger(Utils.class.getName())).setLevel(Level.INFO);
        //        ExtendedLogger logger = ctx.getLogger(Utils.class.getName());
        //        logger.
        //        //LoggerConfig loggerConfig = config.getRootLogger();
        //        logger.(Level.toLevel("info")));
        determineAllowed("/NameGeneratorSyllable-VowelsNoPrevVowels.txt", IConstants.VOWEL, PREVIOUS_VOWEL, //$NON-NLS-1$
            new Syllable("-xa +v")); //$NON-NLS-1$
        ((org.apache.logging.log4j.core.Logger) LogManager.getLogger(Utils.class.getName())).setLevel(Level.TRACE);
    }

    /**
     * Test method for
     * {@link com.parasoft.sa.ctp.SyllableListHolder#determineExpectation(com.parasoft.sa.ctp.Syllable)}.
     */
    @Test
    public void testLoadFromFileExceptions() {
        boolean thrown = false;
        final String l_FileName = "/NameGeneratorSyllable-NoMidVowel.txtX"; //$NON-NLS-1$
        final String fmt = "File: '%1$s' could not be found on the classpath"; //$NON-NLS-1$
        final String expected = String.format(fmt, l_FileName);
        // reset all the Syllable Lists to the initial state
        final ISyllableListHolder test = new SyllableListHolder();
        // final Syllable testSyllable = new Syllable("-xa +v");
        try {
            test.loadListFromFile(l_FileName, SyllableType.MIDDLE);
        } catch (final NameGeneratorException ex) {
            log.info("caught expected {} : {} ", ex.getClass().getSimpleName() //$NON-NLS-1$
                , ex.getMessage());
            assertEquals(expected.trim(), ex.getMessage().trim());
            thrown = true;
        }
        assertTrue("exception was expected", thrown); //$NON-NLS-1$
    }

    /**
     * Test method for {@link com.parasoft.sa.ctp.SyllableListHolder#setSyllables(java.util.List)}.
     */
    @Test
    public void testSetSyllables() {
        final ISyllableListHolder testBase = new SyllableListHolder(null);
        assertNotNull(testBase);
        final ISyllableListHolder testBase2 = new SyllableListHolder(new ArrayList<Syllable>());
        assertNotNull(testBase2);
        final ISyllableListHolder test = new SyllableListHolder();
        assertEquals("Expecting 0 for size", 0, test.size()); //$NON-NLS-1$
        test.setSyllables(null);
        assertEquals("Expecting 0 for size", 0, test.size()); //$NON-NLS-1$
        test.setSyllables(new ArrayList<Syllable>());
        assertEquals("Expecting 0 for size", 0, test.size()); //$NON-NLS-1$
        test.setSyllables(null);
        final boolean res = test.add(null);
        assertTrue("expecting no action for null syllable", !res); //$NON-NLS-1$
        assertEquals("Expecting 0 for size", 0, test.size()); //$NON-NLS-1$
        final ISyllable rnd = test.randomSyllable();
        assertNull(rnd);
        assertEquals(-1, test.getCurrentSyllableIndex());
        test.reset();
        validateSetAddAllSyllables(test,
            new Syllable[] { new Syllable("-xa"), new Syllable("-xs +v"), new Syllable("-xu +c") }); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        assertTrue(test.getType().equals(SyllableType.PREFIX));
        test.reset();
        validateSetAddAllSyllables(test,
            new Syllable[] { new Syllable("xa"), new Syllable("xs +v"), new Syllable("xu +c") }); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        assertTrue(test.getType().equals(SyllableType.MIDDLE));
        validateSetAddAllSyllables(test,
            new Syllable[] { new Syllable("+xa"), new Syllable("+xs +v"), new Syllable("+xu +c") }); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        assertTrue(test.getType().equals(SyllableType.SUFFIX));
        test.addAll(null);
        assertTrue(test.getSyllables().isEmpty());
        test.setSyllables(null);
        assertNull(test.getSyllables());
        final List<Syllable> newList =
            Arrays.asList(new Syllable[] { new Syllable("+xa"), new Syllable("+xs +v"), new Syllable("+xu +c") }); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
        test.addAll(newList);
        doAsserts(test, newList);

    }

    /**
     * <DL>
     * <DT>Description:</DT>
     * <DD>Take the array of Syllables passed in and load the passed SyllableListHolder, then validate that this
     * activity occurred correctly</DD>
     * <DT>Date:</DT>
     * <DD>Jan 7, 2017</DD>
     * </DL>
     *
     * @param test
     */
    private void validateSetAddAllSyllables(final ISyllableListHolder test, final Syllable[] syllablesIn) {
        final List<Syllable> newList = new ArrayList<>();
        newList.addAll(Arrays.asList(syllablesIn));
        test.setSyllables(newList);
        doAsserts(test, newList);
        //validate addAll will behave same as set
        test.addAll(newList);
        doAsserts(test, newList);
    }

}
