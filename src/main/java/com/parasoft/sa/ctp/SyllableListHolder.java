/**
 * @author nrapopor - Nick Rapoport
 * @copyright Copyright 2016 ( Dec 25, 2016 ) Nick Rapoport all rights reserved.
 */
package com.parasoft.sa.ctp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * <DL>
 * <DT>Description:</DT>
 * <DD>This class is used to hold a list of syllables of a particular SyllableType. it will also configure itself
 * according to the current name generation rules as they are read in from the file.
 * <P>
 * For more details please the javadoc for {@link com.parasoft.sa.ctp.NameGenerator}</DD>
 * <DT>Date:</DT>
 * <DD>Dec 25, 2016</DD>
 * </DL>
 *
 * @see com.parasoft.sa.ctp.NameGenerator
 * @author nrapopor - Nick Rapoport
 *
 */
public class SyllableListHolder implements ISyllableListHolder {

    private static org.slf4j.Logger LOG = org.slf4j.LoggerFactory.getLogger(SyllableListHolder.class);

    private List<ISyllable> syllables = Collections.synchronizedList(new ArrayList<>());

    private boolean containsInitialVowels;

    private boolean containsInitialCons;

    private boolean allowsVowels;

    private boolean allowsCons;

    private SyllableType syllableType;

    private final Random randomizer = new Random();

    private int currentSyllableIndex = -1;

    /**
     * <DL>
     * <DT>Description:</DT>
     * <DD>SyllableListHolder Constructor</DD>
     * <DT>Date:</DT>
     * <DD>Dec 25, 2016</DD>
     * </DL>
     */
    public SyllableListHolder() {
        super();
    }

    /**
     * <DL>
     * <DT>Description:</DT>
     * <DD>SyllableListHolder Constructor</DD>
     * <DT>Date:</DT>
     * <DD>Dec 25, 2016</DD>
     * </DL>
     *
     * @param aSyllables
     */
    public SyllableListHolder(final List<? extends ISyllable> aSyllables) {
        super();
        setSyllables(aSyllables);
    }

    /** {@inheritDoc} */
    @Override
    public boolean add(final ISyllable syllable) {
        if (getSyllables() == null) {
            setSyllables(new ArrayList<>());
        }
        if (syllable != null) {
            return getSyllables().add(syllable);
        }
        return false;
    }

    /** {@inheritDoc} */
    @Override
    public boolean addAll(final List<? extends ISyllable> list) {
        resetRules();
        boolean status = false;
        if (getSyllables() == null) {
            setSyllables(new ArrayList<>());
            status = true;
        }
        if (!getSyllables().isEmpty()) {
            getSyllables().clear();
            status = true;
        }
        if (validate(list)) {
            status = getSyllables().addAll(list);
            determineRules();
        }

        return status;
    }

    private boolean allowConsonants() {
        if (isListvalid()) {
            for (final ISyllable s : getSyllables()) {
                if (s.isHatesPreviousVowels() || !s.isHatesPreviousCons()) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean allowVowels() {
        if (isListvalid()) {
            for (final ISyllable s : getSyllables()) {
                if (s.isHatesPreviousCons() || !s.isHatesPreviousVowels()) {
                    return true;
                }
            }
        }
        return false;
    }

    /** {@inheritDoc} */
    @Override
    public boolean contains(final Object aO) {
        return getSyllables().contains(aO);
    }

    private boolean containsInitialConsonant() {
        if (isListvalid()) {
            for (final ISyllable s : getSyllables()) {
                if (s.isStartsWithConsonant()) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean containsInitialVowel() {
        if (isListvalid()) {
            for (final ISyllable s : getSyllables()) {
                if (s.isStartsWithVowel()) {
                    return true;
                }
            }
        }
        return false;
    }

    /** {@inheritDoc} */
    @Override
    public void determineAllowed(final ISyllable previousSyllable) throws NameGeneratorException {
        if (previousSyllable.isEndsWithVowel() && !isAllowsVowels()) {
            throw new NameGeneratorException(String.format(ALLOWED_ERROR_FMT, getType().toString().toLowerCase(),
                IConstants.VOWEL, previousSyllable.getRaw(), PREVIOUS_VOWEL));
        }

        if (previousSyllable.isEndsWithConsonant() && !isAllowsCons()) {
            throw new NameGeneratorException(String.format(ALLOWED_ERROR_FMT, getType().toString().toLowerCase(),
                IConstants.CONSONANT, previousSyllable.getRaw(), PREVIOUS_CONSONANT));
        }

    }

    /** {@inheritDoc} */
    @Override
    public int determineExpectation(final ISyllable left) throws NameGeneratorException {
        int expecting = 0;
        //if (expectsVowel(prefixList.get(a))) {
        if (left.isExpectsVowel()) {
            expecting = 1;
            if (!isContainsInitialVowels()) {
                throw new NameGeneratorException(
                    String.format(EXPECTED_ERROR_FMT, getType().toString().toLowerCase(), IConstants.VOWEL));
            }
        }
        //if (expectsConsonant(prefixList.get(a))) {
        if (left.isExpectsConsonats()) {
            expecting = 2;
            if (!isContainsInitialCons()) {
                throw new NameGeneratorException(
                    String.format(EXPECTED_ERROR_FMT, getType().toString().toLowerCase(), IConstants.CONSONANT));
            }
        }
        return expecting;

    }

    /** {@inheritDoc} */
    @Override
    public void determineRules() {
        if (isListvalid()) {
            final ISyllable s = getSyllables().get(0);
            setType(s.getType());
        }
        setContainsInitialCons(containsInitialConsonant());
        setContainsInitialVowels(containsInitialVowel());
        setAllowsCons(allowConsonants());
        setAllowsVowels(allowVowels());
    }

    /** {@inheritDoc} */
    @Override
    public ISyllable get(final int aIndex) {
        return getSyllables().get(aIndex);
    }

    /** {@inheritDoc} */
    @Override
    public int getCurrentSyllableIndex() {
        return currentSyllableIndex;
    }

    /** {@inheritDoc} */
    @Override
    public Random getRandomizer() {
        return randomizer;
    }

    /** {@inheritDoc} */
    @Override
    public List<ISyllable> getSyllables() {
        return syllables;
    }

    /** {@inheritDoc} */
    @Override
    public SyllableType getType() {
        return syllableType;
    }

    /** {@inheritDoc} */
    @Override
    public boolean isAllowsCons() {
        return allowsCons;
    }

    /** {@inheritDoc} */
    @Override
    public boolean isAllowsVowels() {
        return allowsVowels;
    }

    /** {@inheritDoc} */
    @Override
    public boolean isContainsInitialCons() {
        return containsInitialCons;
    }

    /** {@inheritDoc} */
    @Override
    public boolean isContainsInitialVowels() {
        return containsInitialVowels;
    }

    /** {@inheritDoc} */
    @Override
    public boolean isListvalid() {
        return validate(getSyllables());
    }

    /** {@inheritDoc} */
    @Override
    public void loadListFromFile(final String aFileName, final SyllableType syllableType)
            throws NameGeneratorException {
        final Map<SyllableType, ISyllableListHolder> parts = new LinkedHashMap<>();
        parts.put(syllableType, this);
        Utils.getInstance().refresh(aFileName, parts, LOG);

    }

    /** {@inheritDoc} */
    @Override
    public synchronized ISyllable randomSyllable() {
        if (isListvalid()) {
            setCurrentSyllableIndex(getRandomizer().nextInt(getSyllables().size()));
        } else {
            setCurrentSyllableIndex(-1);
            return null;
        }
        return getSyllables().get(getCurrentSyllableIndex());
    }

    /** {@inheritDoc} */
    @Override
    public void reset() {
        if (isListvalid()) {
            getSyllables().clear();
        }
        resetRules();
    }

    /**
     * <DL>
     * <DT>Description:</DT>
     * <DD>Reset the rules (as expressed by the content of the syllable list). This method is called when the list is
     * about to be cleared and/or re-populated</DD>
     * <DT>Date:</DT>
     * <DD>Dec 25, 2016</DD>
     * </DL>
     */
    protected void resetRules() {
        setType(null);
        setContainsInitialCons(false);
        setContainsInitialVowels(false);
        setAllowsCons(false);
        setAllowsVowels(false);
    }

    /** {@inheritDoc} */
    @Override
    public void setAllowsCons(final boolean aAllowsCons) {
        allowsCons = aAllowsCons;
    }

    /** {@inheritDoc} */
    @Override
    public void setAllowsVowels(final boolean aAllowsVowels) {
        allowsVowels = aAllowsVowels;
    }

    /** {@inheritDoc} */
    @Override
    public void setContainsInitialCons(final boolean aContainsInitialCons) {
        containsInitialCons = aContainsInitialCons;
    }

    /** {@inheritDoc} */
    @Override
    public void setContainsInitialVowels(final boolean aContainsInitialVowels) {
        containsInitialVowels = aContainsInitialVowels;
    }

    /** {@inheritDoc} */
    @Override
    public void setCurrentSyllableIndex(final int aCurrentSyllableIndex) {
        currentSyllableIndex = aCurrentSyllableIndex;
    }

    /** {@inheritDoc} */
    @Override
    public void setSyllables(final List<? extends ISyllable> aSyllables) {
        resetRules();
        if (isListvalid()) {
            syllables.clear();
        }
        if (validate(aSyllables)) {
            syllables = new ArrayList<>(aSyllables);
            determineRules();
        } else if (aSyllables != null && syllables == null) {
            syllables = new ArrayList<>(aSyllables);
        } else if (aSyllables == null) {
            syllables = null;
        }

    }

    /** {@inheritDoc} */
    @Override
    public void setType(final SyllableType aSyllableType) {
        syllableType = aSyllableType;
    }

    /** {@inheritDoc} */
    @Override
    public int size() {
        if (isListvalid()) {
            return getSyllables().size();
        }
        return 0;
    }

    /**
     * <DL>
     * <DT>Description:</DT>
     * <DD>validate a list of syllables. make sure it's not nul;l or empty</DD>
     * <DT>Date:</DT>
     * <DD>Dec 26, 2016</DD>
     * </DL>
     *
     * @param list
     *            - the list to test
     * @return
     *         <DL>
     *         <DT><code>true</code></DT>
     *         <DD>if the passed List is not null or empty</DD>
     *         <DT><code>false</code></DT>
     *         <DD>otherwise</DD>
     *         </DL>
     */
    protected boolean validate(final List<? extends ISyllable> list) {
        return list != null && !list.isEmpty();
    }

}
