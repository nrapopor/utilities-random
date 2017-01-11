/**
 * @author nrapopor - Nick Rapoport
 * @copyright Copyright 2017 ( Jan 7, 2017 ) Nick Rapoport all rights reserved.
 */
package com.parasoft.sa.ctp;

import static org.junit.Assert.*;

/**
 * <DL>
 * <DT>Description:</DT>
 * <DD>some common functions used by multiple test classes</DD>
 * <DT>Date:</DT>
 * <DD>Jan 7, 2017</DD>
 * </DL>
 *
 * @author nrapopor - Nick Rapoport
 *
 */
public abstract class AbstractTestBase implements IConstants {

    protected static org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(AbstractTestBase.class);

    /**
     * <DL>
     * <DT>Description:</DT>
     * <DD>AbstractTestBase Constructor</DD>
     * <DT>Date:</DT>
     * <DD>Jan 7, 2017</DD>
     * </DL>
     */
    public AbstractTestBase() {
        super();
    }

    /**
     * <DL>
     * <DT>Description:</DT>
     * <DD>push a new value into the System property used to load the Syllables for the NameGenerator</DD>
     * <DT>Date:</DT>
     * <DD>Jan 7, 2017</DD>
     * </DL>
     *
     * @return
     */
    public String prepareFile(final String testFile) {
        System.setProperty(INameGenerator.SYLLABLE_FILE, testFile);
        return testFile;
    }

    /**
     * <DL>
     * <DT>Description:</DT>
     * <DD>Convenience method for {@link #testCompose(String, int, boolean)} passes true as the last argument</DD>
     * <DT>Date:</DT>
     * <DD>Jan 7, 2017</DD>
     * </DL>
     *
     * @param expected
     * @param result
     * @param syls
     */
    protected void testCompose(final String expected, final int syls) {
        testCompose(expected, syls, true);
    }

    /**
     * <DL>
     * <DT>Description:</DT>
     * <DD>instantiate the NameGenerator and execute the compose method passing the number of syllables. The last
     * argument specifies if an exception is expected</DD>
     * <DT>Date:</DT>
     * <DD>Jan 7, 2017</DD>
     * </DL>
     *
     * @param expected
     * @param result
     * @param syls
     * @param throwExpected
     */
    protected void testCompose(final String expected, final int syls, final boolean throwExpected) {
        String result = null;
        boolean thrown = false;
        try {
            final INameGenerator test = new NameGenerator();
            result = test.compose(syls);
            log.trace("Composed name : '{}'", result); //$NON-NLS-1$
        } catch (final NameGeneratorException ex) {
            log.info("caught expected {} Error : {} ", ex.getClass().getSimpleName() //$NON-NLS-1$
                , ex.getMessage());
            assertEquals("Expected message", expected.trim(), ex.getMessage().trim()); //$NON-NLS-1$
            thrown = true;
        }
        assertEquals("exception was expected", throwExpected, thrown); //$NON-NLS-1$
    }

    /** {@inheritDoc} */
    public void changeFile(final String fileName) throws NameGeneratorException {
    }

}
