/**
 * @author nrapopor - Nick Rapoport
 * @copyright Copyright 2017 ( Jan 7, 2017 ) Nick Rapoport all rights reserved.
 */
package com.parasoft.sa.ctp;

import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;

import com.parasoft.api.Application;

/**
 * <DL>
 * <DT>Description:</DT>
 * <DD>utility methods that are used by multiple objects</DD>
 * <DT>Date:</DT>
 * <DD>Jan 7, 2017</DD>
 * </DL>
 *
 * @author nrapopor - Nick Rapoport
 *
 */
public class Utils implements IConstants {
    @SuppressWarnings("unused")
    private static org.slf4j.Logger LOG = org.slf4j.LoggerFactory.getLogger(Utils.class);

    private static final Utils instance = new Utils();

    /**
     * <DL>
     * <DT>Description:</DT>
     * <DD>Getter for the instance property</DD>
     * <DT>Date:</DT>
     * <DD>Jan 7, 2017</DD>
     * </DL>
     *
     * @return the value of instance field
     */
    public static Utils getInstance() {
        return instance;
    }

    private Utils() {
        super();
    }

    /**
     * <DL>
     * <DT>Description:</DT>
     * <DD>LOG the passed error and wrap it in the NameGeneratorException</DD>
     * <DT>Date:</DT>
     * <DD>Jan 7, 2017</DD>
     * </DL>
     *
     * @param ex
     * @param msg
     * @returns NameGeneratorException the exception wrapping the passed error and a the message
     */
    public NameGeneratorException logAndWrapException(final Throwable ex, final String msg, final Logger log) {
        logState(ex, msg, log);
        final NameGeneratorException exNew = new NameGeneratorException(msg, ex);
        exNew.fillInStackTrace();
        return exNew;
    }

    /**
     * <DL>
     * <DT>Description:</DT>
     * <DD>LOG the passed error and wrap it in the NameGeneratorException</DD>
     * <DT>Date:</DT>
     * <DD>Jan 7, 2017</DD>
     * </DL>
     *
     * @param ex
     * @param msg
     * @returns NameGeneratorException the exception wrapping the passed error and a the message
     */
    public void logState(final Throwable ex, final String msg, final Logger log) {
        log.error(msg);
        if (ex != null) {
            log.error("caught {} Error : ", ex.getClass().getSimpleName(), ex); //$NON-NLS-1$
        }
        Application.showMessage(msg);
    }

    /**
     * <DL>
     * <DT>Description:</DT>
     * <DD>Refresh Syllable lists from file. No need to call this method, if you are not changing the file during the
     * operation of program, as this method is called every time file name is changed or new NameGenerator object
     * created.</DD>
     * <DT>Date:</DT>
     * <DD>Jan 7, 2017</DD>
     * </DL>
     *
     * @param fileName
     *            the file name to use
     * @param parts
     *            the Map<SyllableType,SyllableListHolder> at most one per allowed type (PREFIX,MIDDLE,SUFFIX)
     * @throws NameGeneratorException
     */
    public void refresh(final String fileName, final Map<SyllableType, ISyllableListHolder> parts, final Logger log)
            throws NameGeneratorException {
        ;
        List<String> lines = null;
        //final String l_FileName = getFileName();
        // reset all the Syllable Lists to the initial state
        for (final ISyllableListHolder holder : parts.values()) {
            holder.reset();
        }
        try {
            final URL resource = this.getClass().getResource(fileName);
            if (resource == null) {
                final String msg = " File: '" + fileName + "' could not be found on the classpath"; //$NON-NLS-1$ //$NON-NLS-2$
                final NameGeneratorException newEx = logAndWrapException(null, msg, log);
                throw newEx;
            }
            lines = Files.readAllLines(Paths.get(resource.toURI()), Charset.defaultCharset());

            for (final String line : lines) {
                if (!line.equals("") && !line.trim().startsWith("#")) { //$NON-NLS-1$ //$NON-NLS-2$
                    final Syllable s = new Syllable(line);
                    final SyllableType syllableType = s.getType();
                    if (parts.containsKey(syllableType)) {
                        parts.get(syllableType).add(s);
                    }
                }
            }
            for (final ISyllableListHolder holder : parts.values()) {
                holder.determineRules();
            }
        } catch (final Exception ex) {
            if (NameGeneratorException.class.isAssignableFrom(ex.getClass())) {
                throw (NameGeneratorException) ex;
            }
            throw logAndWrapException(ex,
                String.format(FILE_READ_ERROR_FMT, "refresh", "File", fileName, ex.getClass().getSimpleName()), log); //$NON-NLS-1$ //$NON-NLS-2$
        }

    }

    /**
     *
     *
     * @throws NameGeneratorException
     */

    /**
     * <DL>
     * <DT>Description:</DT>
     * <DD>as the name implies convert a 2 String array to 2 int array</DD>
     * <DT>Date:</DT>
     * <DD>Jan 9, 2017</DD>
     * </DL>
     *
     * @param countsArray
     *            the array to convert
     * @param defaultCount
     *            use this value for both elements if the array does not contain 2 strings that can converted to
     *            integers
     * @param log
     *            use this for logging errors
     * @return a 2 int array
     */
    public int[] toIntArray(final String[] countsArray, final int defaultCount, final Logger log) {
        final int[] syllableCounts = new int[2];
        for (int i = 0; i < syllableCounts.length; i++) {
            try {
                syllableCounts[i] =
                    countsArray != null && countsArray.length > i ? Integer.parseInt(countsArray[i]) : defaultCount;
            } catch (final NumberFormatException ex) {
                Utils.getInstance().logState(null,
                    String.format(IConstants.MALFORMED_LEN_ERROR, "getRandomName", //$NON-NLS-1$
                        "syllable count for " + (i < 1 ? "First" : "Last") + " name", "" + defaultCount, //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$ //$NON-NLS-5$
                        Arrays.toString(countsArray)),
                    log);
                syllableCounts[i] = defaultCount;
            }
        }
        if (log.isDebugEnabled()) {
            log.debug("The syllable counts [First, Last] to be used are {}", Arrays.toString(syllableCounts)); //$NON-NLS-1$
        }
        return syllableCounts;
    }

}
