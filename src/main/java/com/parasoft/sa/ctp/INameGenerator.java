/**
 * @author nrapopor - Nick Rapoport
 * @copyright Copyright 2017 ( Jan 9, 2017 ) Nick Rapoport all rights reserved.
 */
package com.parasoft.sa.ctp;

import java.util.Map;

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
public interface INameGenerator extends IConstants {

    /**
     * Change the file. refresh() is automatically called during the process.
     *
     * @param fileName
     *            insert the file name, where syllables are located.
     * @throws NameGeneratorException
     *             if the file is null or an empty string
     */
    void changeFile(final String fileName) throws NameGeneratorException;

    /**
     * Compose a new name.
     *
     * @param syls
     *            The number of syllables used in name.
     * @return Returns composed name as a String
     * @throws NameGeneratorException
     *             when logical mistakes are detected inside chosen file, and program is unable to complete the name.
     */
    String compose(final int syls) throws NameGeneratorException;

    /**
     * <DL>
     * <DT>Description:</DT>
     * <DD>Getter for the fileName property</DD>
     * <DT>Date:</DT>
     * <DD>Dec 24, 2016</DD>
     * </DL>
     *
     * @return the value of fileName field
     */
    String getFileName();

    /**
     * <DL>
     * <DT>Description:</DT>
     * <DD>Getter for the parts property</DD>
     * <DT>Date:</DT>
     * <DD>Jan 7, 2017</DD>
     * </DL>
     *
     * @return the value of parts field
     */
    Map<SyllableType, ISyllableListHolder> getParts();

    /**
     * <DL>
     * <DT>Description:</DT>
     * <DD>Getter for the repeatSyllable property</DD>
     * <DT>Date:</DT>
     * <DD>Jan 8, 2017</DD>
     * </DL>
     *
     * @return the value of repeatSyllable field
     */
    boolean isRepeatSyllable();

    /**
     * Refresh names from file. No need to call this method, if you are not changing the file during the operation of
     * program, as this method is called every time file name is changed or new NameGenerator object created.
     *
     *
     * @throws NameGeneratorException
     */
    void refresh() throws NameGeneratorException;

    /**
     * <DL>
     * <DT>Description:</DT>
     * <DD>Setter for the fileName property</DD>
     * <DT>Date:</DT>
     * <DD>Dec 24, 2016</DD>
     * </DL>
     *
     * @param aFileName
     *            new value for the fileName property
     */
    void setFileName(final String aFileName);

    /**
     * <DL>
     * <DT>Description:</DT>
     * <DD>Setter for the repeatSyllable property</DD>
     * <DT>Date:</DT>
     * <DD>Jan 8, 2017</DD>
     * </DL>
     *
     * @param aRepeatSyllable
     *            new value for the repeatSyllable property
     */
    void setRepeatSyllable(final boolean aRepeatSyllable);

}
