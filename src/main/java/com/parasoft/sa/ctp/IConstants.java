/**
 * @author nrapopor - Nick Rapoport
 * @copyright Copyright 2017 ( Jan 9, 2017 ) Nick Rapoport all rights reserved.
 */
package com.parasoft.sa.ctp;

/**
 * <DL>
 * <DT>Description:</DT>
 * <DD>holds all the constants for this package</DD>
 * <DT>Date:</DT>
 * <DD>Jan 9, 2017</DD>
 * </DL>
 *
 * @author nrapopor - Nick Rapoport
 *
 */
public interface IConstants {

    /**
     * <DL>
     * <DT>FIRST_NAME_SYLLABLES</DT>
     * <DD>The name of the System property that can be used to override the default syllables file used for first
     * names</DD>
     * </DL>
     */
    String FIRST_NAME_SYLLABLES = LoadConfig.getString("first-names-syllables-key"); //$NON-NLS-1$

    /**
     * <DL>
     * <DT>LAST_NAME_SYLLABLES</DT>
     * <DD>The name of the System property that can be used to override the default syllables file used for last
     * names</DD>
     * </DL>
     */
    String LAST_NAME_SYLLABLES = LoadConfig.getString("last-names-syllables-key"); //$NON-NLS-1$

    /**
     * <DL>
     * <DT>LAST_NAME_SYLLABLES</DT>
     * <DD>The name of the System property that can be used to override the default syllable count used for name
     * generation</DD>
     * </DL>
     */
    String SYLLABLE_COUNT = LoadConfig.getString("name-generator-syllable-count-key"); //$NON-NLS-1$

    /**
     * <DL>
     * <DT>FIRST_NAME_DEFAULT</DT>
     * <DD>The name of the default syllables file used for first names</DD>
     * </DL>
     */
    String FIRST_NAME_DEFAULT = LoadConfig.getString("first-names-default"); //$NON-NLS-1$

    /**
     * <DL>
     * <DT>LAST_NAME_DEFAULT</DT>
     * <DD>The name of the default syllables file used for last names</DD>
     * </DL>
     */
    String LAST_NAME_DEFAULT = LoadConfig.getString("last-names-default"); //$NON-NLS-1$

    /**
     * <DL>
     * <DT>DEFAULT_LENGTH</DT>
     * <DD>default length (in characters) of the generated random</DD>
     * </DL>
     */
    int DEFAULT_LENGTH = LoadConfig.getInt("length-default", 5); //$NON-NLS-1$

    /**
     * <DL>
     * <DT>DEFAULT_LENGTH</DT>
     * <DD>Default length (in number of syllables) of the generated random name parts
     * <UL>
     * <LI>2 means prefix, and suffix</LI>
     * <LI>3 means prefix, middle and suffix</LI>
     * <LI>4+ means prefix, middle[count - 2] ..., and suffix</LI>
     * </UL>
     * </DD>
     * </DL>
     */
    int DEFAULT_SYLLABLE_COUNT = LoadConfig.getInt("syllable-count-default", 3); //$NON-NLS-1$

    String MALFORMED_LEN_ERROR = LoadConfig.getString("error-mallformed-len"); //$NON-NLS-1$

    String ERROR_LOADING_FILE = LoadConfig.getString("error-file-load"); //$NON-NLS-1$

    String FILE_READ_ERROR_FMT = LoadConfig.getString("error-file-read"); //$NON-NLS-1$

    String VOWELS_STRING = LoadConfig.getString("vowels"); //$NON-NLS-1$
    //= new String(VOWELS);

    char[] VOWELS = VOWELS_STRING.toCharArray();
    //= { 'a', 'e', 'i', 'o', 'u' };

    String CONSONANTS_STRING = LoadConfig.getString("consonants"); //$NON-NLS-1$
    //= new String(CONSONANTS);

    char[] CONSONANTS = CONSONANTS_STRING.toCharArray();
    //    { 'b', 'c', 'd', 'f', 'g', 'h', 'j', 'k', 'l', 'm', 'n', 'p', 'q', 'r', 's', 't', 'v', 'w', 'x', 'y', 'z' };

    /**
     * <DL>
     * <DT>CONSONANT</DT>
     * <DD>used for logging</DD>
     * </DL>
     */
    String CONSONANT = "consonant" //$NON-NLS-1$
    ;

    /**
     * <DL>
     * <DT>VOWEL</DT>
     * <DD>Used for logging</DD>
     * </DL>
     */
    String VOWEL = "vowel"; //$NON-NLS-1$

    /**
     * <DL>
     * <DT>PREVIOUS_VOWEL</DT>
     * <DD>expect preceding syllable to end on a vowel</DD>
     * </DL>
     */
    String PREVIOUS_VOWEL = "-v"; //$NON-NLS-1$

    /**
     * <DL>
     * <DT>PREVIOUS_VOWEL</DT>
     * <DD>expect preceding syllable to end on a consonant</DD>
     * </DL>
     */
    String PREVIOUS_CONSONANT = "-c"; //$NON-NLS-1$

    /**
     * <DL>
     * <DT>PREVIOUS_VOWEL</DT>
     * <DD>expect next syllable to end on a consonant</DD>
     * </DL>
     */
    String NEXT_CONSONANT = "+c"; //$NON-NLS-1$

    /**
     * <DL>
     * <DT>PREVIOUS_VOWEL</DT>
     * <DD>expect next syllable to end on a vowel</DD>
     * </DL>
     */
    String NEXT_VOWEL = "+v"; //$NON-NLS-1$

    String LENGTH = "length"; //$NON-NLS-1$

    String ALLOWED_ERROR_FMT = LoadConfig.getString("error-allowed-fmt"); //$NON-NLS-1$

    String EXPECTED_ERROR_FMT = LoadConfig.getString("error-expected-fmt"); //$NON-NLS-1$

    /**
     * <DL>
     * <DT>SYLLABLE_FILE</DT>
     * <DD>The system property used to override the default syllable file name</DD>
     * </DL>
     */
    String SYLLABLE_FILE = LoadConfig.getString("syllables-file-key"); //$NON-NLS-1$

    /**
     * <DL>
     * <DT>SYLLABLE_FILE</DT>
     * <DD>The system property used to override the default syllable file name</DD>
     * </DL>
     */
    String SYLLABLE_REPEAT = LoadConfig.getString("syllables-allow-repeat-key"); //$NON-NLS-1$

    /**
     * <DL>
     * <DT>SYLLABLE_FILE</DT>
     * <DD>The system property used to override the default syllable file name</DD>
     * </DL>
     */
    String DEFAULT_SYLLABLE_REPEAT = LoadConfig.getString("syllables-allow-repeat-default"); //$NON-NLS-1$

    /**
     * <DL>
     * <DT>LOG_EXPECTATION_STATE</DT>
     * <DD>Logging support format for the found random syllable and expectations</DD>
     * </DL>
     */
    String LOG_EXPECTATION_STATE = LoadConfig.getString("log-expectation-state"); //$NON-NLS-1$

    /**
     * <DL>
     * <DT>MIN_SYLLABLES_ERROR</DT>
     * <DD>Logging support for the number of syllables requested too small</DD>
     * </DL>
     */
    String MIN_SYLLABLES_ERROR = LoadConfig.getString("error-min-syllables"); //$NON-NLS-1$

    /**
     * <DL>
     * <DT>SYLLABLE_FILE_DEFAULT</DT>
     * <DD>The default syllable file name</DD>
     * </DL>
     */
    String SYLLABLE_FILE_DEFAULT = LoadConfig.getString("syllables-file-default"); //$NON-NLS-1$

    String MISSING_MIDDLE_ERROR_FMT = LoadConfig.getString("error-missing-middle-fmt"); //$NON-NLS-1$

    String MISSING_PREFIX_ERROR_FMT = LoadConfig.getString("error-missing-prefix-fmt"); //$NON-NLS-1$

    String MISSING_SUFFIX_ERROR_FMT = LoadConfig.getString("error-missing-suffix-fmt"); //$NON-NLS-1$

    /**
     * <DL>
     * <DT>FILE_NAME_ERROR</DT>
     * <DD>the error message for malformed of missing file name</DD>
     * </DL>
     */
    String FILE_NAME_ERROR = LoadConfig.getString("error-file-name"); //$NON-NLS-1$

}
