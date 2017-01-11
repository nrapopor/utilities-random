/**
 * @author nrapopor - Nick Rapoport
 * @copyright Copyright 2016 ( Dec 23, 2016 ) Nick Rapoport all rights reserved.
 */
package com.parasoft.sa.ctp;

import java.util.Arrays;
import java.util.stream.Stream;

import org.apache.commons.lang3.RandomStringUtils;

import com.parasoft.api.ExtensionToolContext;

/**
 * <DL>
 * <DT>Description:</DT>
 * <DD>Random number and string utilities class, used to generate pseudo-random values for use in SOATest test and
 * Virtualize responder Extension Tools. For the details of the {@link #getRandomName()} please see the
 * {@link com.parasoft.sa.ctp.NameGenerator} class</DD>
 * <DT>Date:</DT>
 * <DD>Dec 23, 2016</DD>
 * </DL>
 *
 * @see com.parasoft.sa.ctp.NameGenerator
 * @author nrapopor - Nick Rapoport
 *
 */
public class RandomUtils implements IConstants {

    private static org.slf4j.Logger LOG = org.slf4j.LoggerFactory.getLogger(RandomUtils.class);

    private static INameGenerator firstNames;

    private static INameGenerator lastNames;

    private static int syllableCount = DEFAULT_SYLLABLE_COUNT;

    private static final IConstants instance = new RandomUtils();

    /**
     * <DL>
     * <DT>Description:</DT>
     * <DD>Getter for the firstNames property</DD>
     * <DT>Date:</DT>
     * <DD>Jan 5, 2017</DD>
     * </DL>
     *
     * @return the value of firstNames field
     */
    public static INameGenerator getFirstNames() {
        if (firstNames == null) {
            resetNames();
        }
        return firstNames;
    }

    /**
     * <DL>
     * <DT>Description:</DT>
     * <DD>This will return a singleton instance of of this class. Here for future use in case static methods will not
     * cut it.</DD>
     * <DT>Date:</DT>
     * <DD>Jan 7, 2017</DD>
     * </DL>
     *
     * @return and instance of RandomUtils
     */
    public static IConstants getIstance() {
        return instance;
    }

    /**
     * <DL>
     * <DT>Description:</DT>
     * <DD>Getter for the lastNames property</DD>
     * <DT>Date:</DT>
     * <DD>Jan 5, 2017</DD>
     * </DL>
     *
     * @return the value of lastNames field
     */
    public static INameGenerator getLastNames() {
        if (lastNames == null) {
            resetNames();
        }
        return lastNames;
    }

    /**
     * <DL>
     * <DT>Description:</DT>
     * <DD>Parasoft API compliant wrapper for {@link #getRandomAlphanumeric(String)}</DD>
     * <DT>Date:</DT>
     * <DD>Jan 5, 2017</DD>
     * </DL>
     *
     * @param req
     *            the string containing the expected length
     * @param ctx
     *            Parasoft ExtensionToolContext (can be null)
     * @return a random of the specified content and length
     */
    public static String getRandomAlphanumeric(final Object req, final ExtensionToolContext ctx) {
        return getRandomAlphanumeric(req.toString());
    }

    /**
     * <DL>
     * <DT>Description:</DT>
     * <DD>return a random string of specified length with alphanumeric characters</DD>
     * <DT>Date:</DT>
     * <DD>Jan 5, 2017</DD>
     * </DL>
     *
     * @param countStr
     *            a string representing a number of characters in the random
     * @return a random of the specified content and length
     */
    public static String getRandomAlphanumeric(final String countStr) {
        int count = DEFAULT_LENGTH;
        try {
            count = Integer.parseInt(countStr);
        } catch (final NumberFormatException ex) {
            Utils.getInstance().logState(ex,
                String.format(MALFORMED_LEN_ERROR, "getRandomAlphanumeric", LENGTH, count, countStr), LOG); //$NON-NLS-1$
        }
        return RandomStringUtils.random(count, true, true);
    }

    /**
     * <DL>
     * <DT>Description:</DT>
     * <DD>return a random name first and last separated by one space. using the default syllable count</DD>
     * <DT>Date:</DT>
     * <DD>Jan 5, 2017</DD>
     * </DL>
     *
     * @param countStr
     *            a string representing a number of syllables in the names
     * @see com.parasoft.sa.ctp.NameGenerator#compose(int)
     * @return a random name (first and last)
     */
    public static synchronized String getRandomName() {
        return getRandomName(new int[] { getSyllableCount(), getSyllableCount() });
    }

    /**
     * <DL>
     * <DT>Description:</DT>
     * <DD>return a random name first and last separated by one space. using the default syllable count</DD>
     * <DT>Date:</DT>
     * <DD>Jan 5, 2017</DD>
     * </DL>
     *
     * @param countStr
     *            a string representing a number of syllables in the names
     * @see com.parasoft.sa.ctp.NameGenerator#compose(int)
     * @return a random name (first and last)
     */
    public static synchronized String getRandomName(final int[] counts) {
        final StringBuilder name = new StringBuilder();
        int[] syllableCounts = counts;
        if (syllableCounts.length < 2) {
            Utils.getInstance().logState(null, String.format(MALFORMED_LEN_ERROR, "getRandomName", "syllable counts", //$NON-NLS-1$ //$NON-NLS-2$
                "" + getSyllableCount() + "," + getSyllableCount(), Arrays.toString(counts)), LOG); //$NON-NLS-1$ //$NON-NLS-2$
            syllableCounts = new int[] { getSyllableCount(), getSyllableCount() };
        }
        try {
            //int count = syllableCounts[0] < 1 ? getSyllableCount() : syllableCounts[0];
            name.append(getFirstNames().compose(syllableCounts[0]));
            LOG.trace("FirstName={}", name); //$NON-NLS-1$
            //count = syllableCounts.length < 2 || syllableCounts[1] < 1 ? getSyllableCount() : syllableCounts[1];
            name.append(" ").append(getLastNames().compose(syllableCounts[1])); //$NON-NLS-1$
            LOG.trace("FirstAndLastName={}", name); //$NON-NLS-1$
        } catch (final NameGeneratorException ex) {
            Utils.getInstance().logState(ex, "Error generating name '" + name.toString() + "' error: " + ex.toString(), //$NON-NLS-1$ //$NON-NLS-2$
                LOG);
        }
        return name.toString();
    }

    /**
     * <DL>
     * <DT>Description:</DT>
     * <DD>Parasoft API compliant wrapper for {@link #getRandomName(String)}</DD>
     * <DT>Date:</DT>
     * <DD>Jan 5, 2017</DD>
     * </DL>
     *
     * @param req
     *            the string containing the expected syllable counts (comma separated)
     * @param ctx
     *            Parasoft ExtensionToolContext (can be null)
     * @return a random of the specified content and length
     */
    public static String getRandomName(final Object req, final ExtensionToolContext ctx) {
        return getRandomName(req.toString());
    }

    /**
     * <DL>
     * <DT>Description:</DT>
     * <DD>return a random name first and last separated by one space. parsing the syllable counts from the passed
     * string (expected format is "integer,integer"</DD>
     * <DT>Date:</DT>
     * <DD>Jan 5, 2017</DD>
     * </DL>
     *
     * @param countStr
     *            a string representing a number of syllables in the names separated by a semicolon
     * @see com.parasoft.sa.ctp.NameGenerator#compose(int)
     * @return a random name (first and last)
     */
    public static synchronized String getRandomName(final String counts) {
        return getRandomName(Utils.getInstance().toIntArray(Stream.of(counts.split(",\\s*")).toArray(String[]::new), //$NON-NLS-1$
            getSyllableCount(), LOG));
    }

    /**
     * <DL>
     * <DT>Description:</DT>
     * <DD>return a random name first and last separated by one space. parsing the syllable counts from the passed array
     * expecting two string representation of an integer</DD>
     * <DT>Date:</DT>
     * <DD>Jan 5, 2017</DD>
     * </DL>
     *
     * @param countsArray
     *            a string array with 2 elements each representing a number of syllables in the first and last name
     * @see com.parasoft.sa.ctp.NameGenerator#compose(int)
     * @return a random name (first and last)
     */
    public static String getRandomName(final String[] countsArray) {
        return getRandomName(Utils.getInstance().toIntArray(countsArray, getSyllableCount(), LOG));
    }

    /**
     * <DL>
     * <DT>Description:</DT>
     * <DD>Parasoft API compliant wrapper for {@link #getRandomNumber(String)}</DD>
     * <DT>Date:</DT>
     * <DD>Jan 5, 2017</DD>
     * </DL>
     *
     * @param req
     *            the string containing the expected length
     * @param ctx
     *            Parasoft ExtensionToolContext (can be null)
     * @return a random of the specified content and length
     */
    public static String getRandomNumber(final Object req, final ExtensionToolContext ctx) {
        return getRandomNumber(req.toString());
    }

    /**
     * <DL>
     * <DT>Description:</DT>
     * <DD>return a random string of specified length with numeric characters only</DD>
     * <DT>Date:</DT>
     * <DD>Jan 5, 2017</DD>
     * </DL>
     *
     * @param countStr
     *            a string representing a number of characters in the random
     * @return a random of the specified content and length
     */
    public static String getRandomNumber(final String countStr) {
        int count = DEFAULT_LENGTH;
        try {
            count = Integer.parseInt(countStr);
        } catch (final NumberFormatException ex) {
            Utils.getInstance().logState(ex,
                String.format(MALFORMED_LEN_ERROR, "getRandomNumber", LENGTH, count, countStr), LOG); //$NON-NLS-1$
        }
        return RandomStringUtils.random(count, false, true);
    }

    /**
     * <DL>
     * <DT>Description:</DT>
     * <DD>Parasoft API compliant wrapper for {@link #getRandomString(String)}</DD>
     * <DT>Date:</DT>
     * <DD>Jan 5, 2017</DD>
     * </DL>
     *
     * @param req
     *            the string containing the expected length
     * @param ctx
     *            Parasoft ExtensionToolContext (can be null)
     * @return a random of the specified content and length
     */
    public static String getRandomString(final Object req, final ExtensionToolContext ctx) {
        return getRandomString(req.toString());
    }

    /**
     * <DL>
     * <DT>Description:</DT>
     * <DD>return a random string of specified length with alphabetic characters only</DD>
     * <DT>Date:</DT>
     * <DD>Jan 5, 2017</DD>
     * </DL>
     *
     * @param countStr
     *            a string representing a number of characters in the random
     * @return a random of the specified content and length
     */
    public static String getRandomString(final String countStr) {
        int count = DEFAULT_LENGTH;
        try {
            count = Integer.parseInt(countStr);
        } catch (final NumberFormatException ex) {
            Utils.getInstance().logState(ex,
                String.format(MALFORMED_LEN_ERROR, "getRandomString", LENGTH, count, countStr), LOG); //$NON-NLS-1$
        }
        return RandomStringUtils.random(count, true, false);
    }

    /**
     * <DL>
     * <DT>Description:</DT>
     * <DD>Getter for the syllableCount property</DD>
     * <DT>Date:</DT>
     * <DD>Jan 5, 2017</DD>
     * </DL>
     *
     * @return the value of syllableCount field
     */
    public static int getSyllableCount() {
        return syllableCount;
    }

    /**
     * <DL>
     * <DT>Description:</DT>
     * <DD>initialize first and last name generators</DD>
     * <DT>Date:</DT>
     * <DD>Jan 5, 2017</DD>
     * </DL>
     */
    public static synchronized void resetNames() {
        String currentFile = ""; //$NON-NLS-1$
        final String countStr = System.getProperty(SYLLABLE_COUNT, "" + DEFAULT_SYLLABLE_COUNT); //$NON-NLS-1$

        try {
            setSyllableCount(Integer.parseInt(countStr));
        } catch (final NumberFormatException ex) {
            setSyllableCount(DEFAULT_SYLLABLE_COUNT);
            Utils.getInstance().logState(ex,
                String.format(MALFORMED_LEN_ERROR, "resetNames", "Syllable count", getSyllableCount(), countStr), LOG); //$NON-NLS-1$ //$NON-NLS-2$
        }
        try {
            currentFile = System.getProperty(FIRST_NAME_SYLLABLES, FIRST_NAME_DEFAULT);
            setFirstNames(new NameGenerator(currentFile));
            currentFile = System.getProperty(LAST_NAME_SYLLABLES, LAST_NAME_DEFAULT);
            setLastNames(new NameGenerator(currentFile));
        } catch (final NameGeneratorException ex) {
            Utils.getInstance().logState(ex,
                String.format(ERROR_LOADING_FILE, "resetNames", "NameGenerator", currentFile, ex.getMessage()), LOG); //$NON-NLS-1$ //$NON-NLS-2$
        }
    }

    /**
     * <DL>
     * <DT>Description:</DT>
     * <DD>Setter for the firstNames property</DD>
     * <DT>Date:</DT>
     * <DD>Jan 5, 2017</DD>
     * </DL>
     *
     * @param aFirstNames
     *            new value for the firstNames property
     */
    protected static void setFirstNames(final INameGenerator aFirstNames) {
        firstNames = aFirstNames;
    }

    /**
     * <DL>
     * <DT>Description:</DT>
     * <DD>Setter for the lastNames property</DD>
     * <DT>Date:</DT>
     * <DD>Jan 5, 2017</DD>
     * </DL>
     *
     * @param aLastNames
     *            new value for the lastNames property
     */
    protected static void setLastNames(final INameGenerator aLastNames) {
        lastNames = aLastNames;
    }

    /**
     * <DL>
     * <DT>Description:</DT>
     * <DD>Setter for the syllableCount property</DD>
     * <DT>Date:</DT>
     * <DD>Jan 5, 2017</DD>
     * </DL>
     *
     * @param aSyllableCount
     *            new value for the syllableCount property
     */
    private static void setSyllableCount(final int aSyllableCount) {
        syllableCount = aSyllableCount;
    }

    /**
     * <DL>
     * <DT>Description:</DT>
     * <DD>RandomUtils Constructor</DD>
     * <DT>Date:</DT>
     * <DD>Dec 23, 2016</DD>
     * </DL>
     */
    private RandomUtils() {
        super();
    }

}
