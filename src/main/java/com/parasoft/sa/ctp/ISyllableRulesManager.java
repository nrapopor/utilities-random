/**
 * @author nrapopor - Nick Rapoport
 * @copyright Copyright 2017 ( Jan 11, 2017 ) Nick Rapoport all rights reserved.
 */
package com.parasoft.sa.ctp;

/**
 * <DL>
 * <DT>Description:</DT>
 * <DD>TODO add description</DD>
 * <DT>Date:</DT>
 * <DD>Jan 11, 2017</DD>
 * </DL>
 *
 * @author nrapopor - Nick Rapoport
 *
 */
public interface ISyllableRulesManager extends IConstants {

    /**
     * <DL>
     * <DT>Description:</DT>
     * <DD>construct a name according to the rules</DD>
     * <DT>Date:</DT>
     * <DD>Jan 10, 2017</DD>
     * </DL>
     *
     * @param repeatSyllableAllowed
     *            are repeated middle syllables allowed
     * @return the name constructed according to the rules
     * @throws NameGeneratorException
     *             if an error or a rule violation has occurred
     */
    StringBuilder constructName(boolean repeatSyllableAllowed) throws NameGeneratorException;

    /**
     * <DL>
     * <DT>Description:</DT>
     * <DD>return the reference to the middle (root) syllables</DD>
     * <DT>Date:</DT>
     * <DD>Jan 10, 2017</DD>
     * </DL>
     *
     * @return list of available middle syllables
     */
    ISyllableListHolder getMiddle();

    /**
     * <DL>
     * <DT>Description:</DT>
     * <DD>return the reference to the prefix syllables</DD>
     * <DT>Date:</DT>
     * <DD>Jan 10, 2017</DD>
     * </DL>
     *
     * @return list of available prefix syllables
     */
    ISyllableListHolder getPrefix();

    /**
     * <DL>
     * <DT>Description:</DT>
     * <DD>return the reference to the prefix syllables</DD>
     * <DT>Date:</DT>
     * <DD>Jan 10, 2017</DD>
     * </DL>
     *
     * @return list of available prefix syllables
     */
    ISyllableListHolder getSuffix();

    /**
     * <DL>
     * <DT>Description:</DT>
     * <DD>Getter for the syllableCount property</DD>
     * <DT>Date:</DT>
     * <DD>Jan 9, 2017</DD>
     * </DL>
     *
     * @return the value of syllableCount field
     */
    int getSyllableCount();

    /**
     * <DL>
     * <DT>Description:</DT>
     * <DD>validates the expected syllable count against the available syllables. insures minimum compatibility</DD>
     * <DT>Date:</DT>
     * <DD>Jan 10, 2017</DD>
     * </DL>
     *
     * @param filename
     *            the name of the used to populate the syllable lists
     * @throws NameGeneratorExceptionif
     *             there is no way to fulfill the request
     */
    void validate(String filename) throws NameGeneratorException;

}
