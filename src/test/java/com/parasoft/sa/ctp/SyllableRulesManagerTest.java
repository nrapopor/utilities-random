/**
 * @author nrapopor - Nick Rapoport
 * @copyright Copyright 2017 ( Jan 10, 2017 ) Nick Rapoport all rights reserved.
 */
package com.parasoft.sa.ctp;

import static org.junit.Assert.*;

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
 * <DD>Jan 10, 2017</DD>
 * </DL>
 *
 * @author nrapopor - Nick Rapoport
 *
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class SyllableRulesManagerTest extends SyllableRulesManager implements IConstants {
    private static org.slf4j.Logger LOG = org.slf4j.LoggerFactory.getLogger(SyllableRulesManagerTest.class);

    private static INameGenerator nameGenerator = null;

    /**
     * <DL>
     * <DT>Description:</DT>
     * <DD>Getter for the nameGenerator property</DD>
     * <DT>Date:</DT>
     * <DD>Jan 10, 2017</DD>
     * </DL>
     *
     * @return the value of nameGenerator field
     */
    public static INameGenerator getNameGenerator() {
        return nameGenerator;
    }

    /**
     * <DL>
     * <DT>Description:</DT>
     * <DD>log and fail on unexpected NameGeneratorException</DD>
     * <DT>Date:</DT>
     * <DD>Jan 10, 2017</DD>
     * </DL>
     *
     * @param ex
     */
    public static void precessUnxpectedException(final NameGeneratorException ex) {
        final String msg = String.format("caught unexpected %1$s : %2$s ", ex.getClass().getSimpleName() //$NON-NLS-1$
            , ex.getMessage());
        LOG.error(msg, ex);
        fail(msg);
    }

    /**
     * <DL>
     * <DT>Description:</DT>
     * <DD>Setter for the nameGenerator property</DD>
     * <DT>Date:</DT>
     * <DD>Jan 10, 2017</DD>
     * </DL>
     *
     * @param aNameGenerator
     *            new value for the nameGenerator property
     */
    public static void setNameGenerator(final INameGenerator aNameGenerator) {
        nameGenerator = aNameGenerator;
    }

    /**
     * <DL>
     * <DT>Description:</DT>
     * <DD>setup before class</DD>
     * <DT>Date:</DT>
     * <DD>Jan 7, 2017</DD>
     * </DL>
     *
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        final String testFile = "/NameGeneratorSyllableLast.txt"; //$NON-NLS-1$
        System.setProperty(IConstants.SYLLABLE_FILE, testFile);
        try {
            setNameGenerator(new NameGenerator());
            assertNotNull(getNameGenerator());
        } catch (final NameGeneratorException ex) {
            precessUnxpectedException(ex);
        }

    }

    /**
     * <DL>
     * <DT>Description:</DT>
     * <DD>tear down after class</DD>
     * <DT>Date:</DT>
     * <DD>Jan 7, 2017</DD>
     * </DL>
     *
     * @throws java.lang.Exception
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        setNameGenerator(null);
    }

    /**
     * <DL>
     * <DT>Description:</DT>
     * <DD>SyllableRulesManagerTest Constructor</DD>
     * <DT>Date:</DT>
     * <DD>Jan 11, 2017</DD>
     * </DL>
     *
     * @param aSyllableCount
     * @param aAllSyllables
     */
    public SyllableRulesManagerTest() {
        super(3, getNameGenerator().getParts());
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
     * {@link com.parasoft.sa.ctp.SyllableRulesManager#addMiddleToName(java.lang.StringBuilder, boolean)}.
     */
    @Test
    public final void testAddToName() {
        boolean duplicates = true;
        for (int i = 1; i < 11; i++) {
            final SyllableRulesManager srmUdetTest = new SyllableRulesManager(i, getNameGenerator().getParts());
            final StringBuilder name = new StringBuilder();
            assertNotNull(srmUdetTest.addPrefixToName(name));
            try {
                ((org.apache.logging.log4j.core.Logger) LogManager.getLogger(SyllableRulesManager.class.getName()))
                    .setLevel(Level.INFO);
                assertNotNull(srmUdetTest.addMiddleToName(name, duplicates));
                duplicates = !duplicates;
                assertNotNull(srmUdetTest.addSuffixToName(name));
                LOG.info("Name '{}' count: '{}'", name, i);
                ((org.apache.logging.log4j.core.Logger) LogManager.getLogger(SyllableRulesManager.class.getName()))
                    .setLevel(Level.TRACE);
            } catch (final NameGeneratorException ex) {
                precessUnxpectedException(ex);
            }
        }
    }

    /**
     * Test method for
     * {@link com.parasoft.sa.ctp.SyllableRulesManager#addMiddleToName(java.lang.StringBuilder, boolean)}.
     */
    @Test
    public final void testAddToNameNull() {
        for (int i = 1; i < 5; i++) {
            final SyllableRulesManager srmUdetTest = new SyllableRulesManager(i, getNameGenerator().getParts());
            assertNotNull(srmUdetTest.addPrefixToName(null));
            try {
                assertNotNull(srmUdetTest.addMiddleToName(null, false));
                assertNotNull(srmUdetTest.addSuffixToName(null));
            } catch (final NameGeneratorException ex) {
                precessUnxpectedException(ex);
            }
        }
    }

}
