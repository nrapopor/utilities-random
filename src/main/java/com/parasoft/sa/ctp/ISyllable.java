/**
 * @author nrapopor - Nick Rapoport
 * @copyright Copyright 2017 ( Jan 9, 2017 ) Nick Rapoport all rights reserved.
 */
package com.parasoft.sa.ctp;

/**
 * <DL>
 * <DT>Description:</DT>
 * <DD>TODO add description</DD>
 * <DT>Date:</DT>
 * <DD>Jan 9, 2017</DD>
 * </DL>
 *
 * @author nrapopor - Nick Rapoport
 *
 */
public interface ISyllable extends IConstants {

    /**
     * <DL>
     * <DT>Description:</DT>
     * <DD>Getter for the pure property</DD>
     * <DT>Date:</DT>
     * <DD>Dec 25, 2016</DD>
     * </DL>
     *
     * @return the value of pure field
     */
    String getPure();

    /**
     * <DL>
     * <DT>Description:</DT>
     * <DD>Getter for the raw property</DD>
     * <DT>Date:</DT>
     * <DD>Dec 25, 2016</DD>
     * </DL>
     *
     * @return the value of raw field
     */
    String getRaw();

    /**
     * <DL>
     * <DT>Description:</DT>
     * <DD>Getter for the syllableType property</DD>
     * <DT>Date:</DT>
     * <DD>Dec 25, 2016</DD>
     * </DL>
     *
     * @return the value of syllableType field
     */
    SyllableType getType();

    /**
     * <DL>
     * <DT>Description:</DT>
     * <DD>Getter for the endsWithConsonant property</DD>
     * <DT>Date:</DT>
     * <DD>Dec 25, 2016</DD>
     * </DL>
     *
     * @return the value of endsWithConsonant field
     */
    boolean isEndsWithConsonant();

    /**
     * <DL>
     * <DT>Description:</DT>
     * <DD>Getter for the endsWithVowel property</DD>
     * <DT>Date:</DT>
     * <DD>Dec 25, 2016</DD>
     * </DL>
     *
     * @return the value of endsWithVowel field
     */
    boolean isEndsWithVowel();

    /**
     * <DL>
     * <DT>Description:</DT>
     * <DD>Getter for the expectsConsonats property</DD>
     * <DT>Date:</DT>
     * <DD>Dec 25, 2016</DD>
     * </DL>
     *
     * @return the value of expectsConsonats field
     */
    boolean isExpectsConsonats();

    /**
     * <DL>
     * <DT>Description:</DT>
     * <DD>Getter for the expectsVowel property</DD>
     * <DT>Date:</DT>
     * <DD>Dec 25, 2016</DD>
     * </DL>
     *
     * @return the value of expectsVowel field
     */
    boolean isExpectsVowel();

    /**
     * <DL>
     * <DT>Description:</DT>
     * <DD>Getter for the hatesPreviousCons property</DD>
     * <DT>Date:</DT>
     * <DD>Dec 25, 2016</DD>
     * </DL>
     *
     * @return the value of hatesPreviousCons field
     */
    boolean isHatesPreviousCons();

    /**
     * <DL>
     * <DT>Description:</DT>
     * <DD>Getter for the hatesPreviousVowels property</DD>
     * <DT>Date:</DT>
     * <DD>Dec 25, 2016</DD>
     * </DL>
     *
     * @return the value of hatesPreviousVowels field
     */
    boolean isHatesPreviousVowels();

    /**
     * <DL>
     * <DT>Description:</DT>
     * <DD>Getter for the startsWithConsonant property</DD>
     * <DT>Date:</DT>
     * <DD>Dec 25, 2016</DD>
     * </DL>
     *
     * @return the value of startsWithConsonant field
     */
    boolean isStartsWithConsonant();

    /**
     * <DL>
     * <DT>Description:</DT>
     * <DD>Getter for the startsWithVowel property</DD>
     * <DT>Date:</DT>
     * <DD>Dec 25, 2016</DD>
     * </DL>
     *
     * @return the value of startsWithVowel field
     */
    boolean isStartsWithVowel();

}
