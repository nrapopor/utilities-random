/**
 * @author nrapopor - Nick Rapoport
 * @copyright Copyright 2016 ( Dec 25, 2016 ) Nick Rapoport all rights reserved.
 */
package com.parasoft.sa.ctp;

/**
 * <DL>
 * <DT>Description:</DT>
 * <DD>The object describing a single syllable and any rules associated with it</DD>
 * <DT>Date:</DT>
 * <DD>Dec 25, 2016</DD>
 * </DL>
 *
 * @see com.parasoft.sa.ctp.NameGenerator
 * @author nrapopor - Nick Rapoport
 *
 */
public class Syllable implements ISyllable {
    @SuppressWarnings("unused")
    private static org.slf4j.Logger LOG = org.slf4j.LoggerFactory.getLogger(Syllable.class);

    private final String raw;

    private final String pure;

    private final SyllableType syllableType;

    private final boolean startsWithVowel;

    private final boolean startsWithConsonant;

    private final boolean endsWithVowel;

    private final boolean endsWithConsonant;

    private final boolean hatesPreviousVowels;

    private final boolean hatesPreviousCons;

    private final boolean expectsVowel;

    private final boolean expectsConsonats;

    /**
     * <DL>
     * <DT>Description:</DT>
     * <DD>Syllable Constructor</DD>
     * <DT>Date:</DT>
     * <DD>Dec 25, 2016</DD>
     * </DL>
     */
    public Syllable(final String aRaw) {
        super();
        raw = aRaw;
        pure = pureSyl(getRaw()).toLowerCase();
        syllableType = determineType(getRaw());
        startsWithVowel = vowelFirst(getPure());
        startsWithConsonant = !isStartsWithVowel();
        endsWithVowel = vowelLast(getPure());
        endsWithConsonant = !isEndsWithVowel();

        switch (getType()) {
            case PREFIX:
                expectsVowel = expectsVowel(getRaw());
                expectsConsonats = expectsConsonant(getRaw());
                hatesPreviousVowels = true;
                hatesPreviousCons = true;
                break;

            case SUFFIX:
                expectsVowel = false;
                expectsConsonats = false;
                hatesPreviousVowels = hatesPreviousVowels(getRaw());
                hatesPreviousCons = hatesPreviousConsonants(getRaw());
                break;
            default:
                expectsVowel = expectsVowel(getRaw());
                expectsConsonats = expectsConsonant(getRaw());
                hatesPreviousVowels = hatesPreviousVowels(getRaw());
                hatesPreviousCons = hatesPreviousConsonants(getRaw());
        }
    }

    private SyllableType determineType(final String rawSyllable) {
        switch (rawSyllable.charAt(0)) {
            case '+':
                return SyllableType.SUFFIX;

            case '-':
                return SyllableType.PREFIX;

            default:
                return SyllableType.MIDDLE;
        }
    }

    private boolean expectsConsonant(final String s) {
        if (s.substring(1).contains(NEXT_CONSONANT)) {
            return true;
        } else {
            return false;
        }
    }

    private boolean expectsVowel(final String s) {
        if (s.substring(1).contains(NEXT_VOWEL)) {
            return true;
        } else {
            return false;
        }
    }

    /** {@inheritDoc} */
    @Override
    public String getPure() {
        return pure;
    }

    /** {@inheritDoc} */
    @Override
    public String getRaw() {
        return raw;
    }

    /** {@inheritDoc} */
    @Override
    public SyllableType getType() {
        return syllableType;
    }

    /**
     * <DL>
     * <DT>Description:</DT>
     * <DD>Returns this syllable's opinion on the syllables ending in a consonant to it's left in the name</DD>
     * <DT>Date:</DT>
     * <DD>Dec 25, 2016</DD>
     * </DL>
     *
     * @param s
     * @return
     *         <DL>
     *         <DT><code>true</code></DT>
     *         <DD>if the previous syllable in the name can end in a consonant</DD>
     *         <DT><code>false</code></DT>
     *         <DD>otherwise</DD>
     *         </DL>
     */
    private boolean hatesPreviousConsonants(final String s) {
        if (s.substring(1).contains(PREVIOUS_VOWEL)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * <DL>
     * <DT>Description:</DT>
     * <DD>Returns this syllable's opinion on the syllables ending in a vowel to it's left in the name</DD>
     * <DT>Date:</DT>
     * <DD>Dec 25, 2016</DD>
     * </DL>
     *
     * @param s
     * @return
     *         <DL>
     *         <DT><code>true</code></DT>
     *         <DD>if the previous syllable in the name can end in a vowel</DD>
     *         <DT><code>false</code></DT>
     *         <DD>otherwise</DD>
     *         </DL>
     */
    private boolean hatesPreviousVowels(final String s) {
        if (s.substring(1).contains(PREVIOUS_CONSONANT)) {
            return true;
        } else {
            return false;
        }
    }

    /** {@inheritDoc} */
    @Override
    public boolean isEndsWithConsonant() {
        return endsWithConsonant;
    }

    /** {@inheritDoc} */
    @Override
    public boolean isEndsWithVowel() {
        return endsWithVowel;
    }

    /** {@inheritDoc} */
    @Override
    public boolean isExpectsConsonats() {
        return expectsConsonats;
    }

    /** {@inheritDoc} */
    @Override
    public boolean isExpectsVowel() {
        return expectsVowel;
    }

    /** {@inheritDoc} */
    @Override
    public boolean isHatesPreviousCons() {
        return hatesPreviousCons;
    }

    /** {@inheritDoc} */
    @Override
    public boolean isHatesPreviousVowels() {
        return hatesPreviousVowels;
    }

    /** {@inheritDoc} */
    @Override
    public boolean isStartsWithConsonant() {
        return startsWithConsonant;
    }

    /** {@inheritDoc} */
    @Override
    public boolean isStartsWithVowel() {
        return startsWithVowel;
    }

    private String pureSyl(String s) {
        s = s.trim();
        if (s.charAt(0) == '+' || s.charAt(0) == '-') {
            s = s.substring(1);
        }
        return s.split(" ")[0]; //$NON-NLS-1$
    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return getRaw();
    }

    /**
     * <DL>
     * <DT>Description:</DT>
     * <DD>determine if the first character is a vowel</DD>
     * <DT>Date:</DT>
     * <DD>Dec 24, 2016</DD>
     * </DL>
     *
     * @param s
     * @return
     *         <DL>
     *         <DT><code>true</code></DT>
     *         <DD>if the first character is a vowel</DD>
     *         <DT><code>false</code></DT>
     *         <DD>otherwise</DD>
     *         </DL>
     */
    private boolean vowelFirst(final String s) {
        return VOWELS_STRING.contains(s.substring(0, 1).toLowerCase());
    }

    /**
     * <DL>
     * <DT>Description:</DT>
     * <DD>determine if the last character is a vowel</DD>
     * <DT>Date:</DT>
     * <DD>Dec 24, 2016</DD>
     * </DL>
     *
     * @param s
     * @return
     *         <DL>
     *         <DT><code>true</code></DT>
     *         <DD>if the last character is a vowel</DD>
     *         <DT><code>false</code></DT>
     *         <DD>otherwise</DD>
     *         </DL>
     */
    private boolean vowelLast(final String s) {
        return VOWELS_STRING.contains(s.substring(s.length() - 1).toLowerCase());
    }
}
