/**
 * @author nrapopor - Nick Rapoport
 * @copyright Copyright 2017 ( Jan 7, 2017 ) Nick Rapoport all rights reserved.
 */
package com.parasoft.sa.ctp;

import java.util.List;
import java.util.Random;

/**
 * <DL>
 * <DT>Description:</DT>
 * <DD>Interface that describes the SyllableHolderList class</DD>
 * <DT>Date:</DT>
 * <DD>Jan 7, 2017</DD>
 * </DL>
 *
 * @author nrapopor - Nick Rapoport
 *
 */
public interface ISyllableListHolder extends IConstants {

    /**
     * {@inheritDoc}
     * <DL>
     * <DT>Description:</DT>
     * <DD>add a Syllable to the list</DD>
     * <DT>Date:</DT>
     * <DD>Dec 25, 2016</DD>
     * </DL>
     *
     * @param syllable
     *            - the syllable to add to the list
     * @return
     *         <DL>
     *         <DT><code>true</code></DT>
     *         <DD>if this List of Syllables will be changed by this operation, passing null syllables will have no
     *         effect</DD>
     *         <DT><code>false</code></DT>
     *         <DD>otherwise</DD>
     *         </DL>
     * @see java.util.ArrayList#add(java.lang.Object)
     */
    boolean add(ISyllable syllable);

    /**
     * {@inheritDoc}
     * <DL>
     * <DT>Description:</DT>
     * <DD>remove all the old syllables from the list and add the new ones from the passed list. passing an null or
     * empty list will have the side effect of clearing this list</DD>
     * <DT>Date:</DT>
     * <DD>Dec 25, 2016</DD>
     * </DL>
     *
     * @param aC
     * @return
     *         <DL>
     *         <DT><code>true</code></DT>
     *         <DD>if then list has been changed by this operation</DD>
     *         <DT><code>false</code></DT>
     *         <DD>otherwise</DD>
     *         </DL>
     * @see java.util.ArrayList#addAll(java.util.Collection)
     */
    boolean addAll(List<? extends ISyllable> list);

    /**
     * {@inheritDoc}
     * <DL>
     * <DT>Description:</DT>
     * <DD>Check if the Syllable exists in the list</DD>
     * <DT>Date:</DT>
     * <DD>Dec 25, 2016</DD>
     * </DL>
     *
     * @param aO
     * @return
     * @see java.util.ArrayList#contains(java.lang.Object)
     */
    boolean contains(Object aO);

    /**
     * <DL>
     * <DT>Description:</DT>
     * <DD>make sure that this SyllableList can fulfill the requirements for the previous syllable</DD>
     * <DT>Date:</DT>
     * <DD>Jan 7, 2017</DD>
     * </DL>
     *
     * @param previousSyllable
     *            the syllable that precedes the next random syllable to be picked from this list
     * @throws NameGeneratorException
     *             if there are no syllables in this list that can fulfill the requirements for the passed syllable
     */
    void determineAllowed(ISyllable previousSyllable) throws NameGeneratorException;

    /**
     * <DL>
     * <DT>Description:</DT>
     * <DD>Given the syllable (left) determine if the syllable expects the next syllable to start with a vowel or a
     * consonant, and such syllables are available if the list (right)</DD>
     * <DT>Date:</DT>
     * <DD>Dec 26, 2016</DD>
     * </DL>
     *
     * @param left
     *            -- the left side syllable
     * @param right
     *            -- the list the next syllable will be taken from
     * @return
     *         <DL>
     *         <DT><code>1</code></DT>
     *         <DD>the syllable on the left expects the next syllable to start with a vowel</DD>
     *         <DT><code>2</code></DT>
     *         <DD>the syllable on the left expects the next syllable to start with a consonant</DD>
     *         </DL>
     * @throws NameGeneratorException
     */
    int determineExpectation(ISyllable left) throws NameGeneratorException;

    /**
     * <DL>
     * <DT>Description:</DT>
     * <DD>Determine the state of the rules from the content of the Syllable list</DD>
     * <DT>Date:</DT>
     * <DD>Dec 25, 2016</DD>
     * </DL>
     */
    void determineRules();

    /**
     * {@inheritDoc}
     * <DL>
     * <DT>Description:</DT>
     * <DD>get the syllable from the list by index</DD>
     * <DT>Date:</DT>
     * <DD>Dec 25, 2016</DD>
     * </DL>
     *
     * @param aIndex
     * @return
     * @see java.util.ArrayList#get(int)
     */
    ISyllable get(int aIndex);

    /**
     * <DL>
     * <DT>Description:</DT>
     * <DD>Getter for the currentSyllableIndex property</DD>
     * <DT>Date:</DT>
     * <DD>Dec 25, 2016</DD>
     * </DL>
     *
     * @return the value of currentSyllableIndex field
     */
    int getCurrentSyllableIndex();

    /**
     * <DL>
     * <DT>Description:</DT>
     * <DD>Getter for the randomizer property</DD>
     * <DT>Date:</DT>
     * <DD>Dec 25, 2016</DD>
     * </DL>
     *
     * @return the value of randomizer field
     */
    Random getRandomizer();

    /**
     * <DL>
     * <DT>Description:</DT>
     * <DD>Getter for the syllables property</DD>
     * <DT>Date:</DT>
     * <DD>Dec 25, 2016</DD>
     * </DL>
     *
     * @return the value of syllables field
     */
    List<? extends ISyllable> getSyllables();

    /**
     * <DL>
     * <DT>Description:</DT>
     * <DD>Getter for the type property</DD>
     * <DT>Date:</DT>
     * <DD>Dec 26, 2016</DD>
     * </DL>
     *
     * @return the value of type field
     */
    SyllableType getType();

    /**
     * <DL>
     * <DT>Description:</DT>
     * <DD>Getter for the allowsCons property</DD>
     * <DT>Date:</DT>
     * <DD>Dec 25, 2016</DD>
     * </DL>
     *
     * @return the value of allowsCons field
     */
    boolean isAllowsCons();

    /**
     * <DL>
     * <DT>Description:</DT>
     * <DD>Getter for the allowsVowels property</DD>
     * <DT>Date:</DT>
     * <DD>Dec 25, 2016</DD>
     * </DL>
     *
     * @return the value of allowsVowels field
     */
    boolean isAllowsVowels();

    /**
     * <DL>
     * <DT>Description:</DT>
     * <DD>Getter for the containsInitialCons property</DD>
     * <DT>Date:</DT>
     * <DD>Dec 25, 2016</DD>
     * </DL>
     *
     * @return the value of containsInitialCons field
     */
    boolean isContainsInitialCons();

    /**
     * <DL>
     * <DT>Description:</DT>
     * <DD>Getter for the containsInitialVowels property</DD>
     * <DT>Date:</DT>
     * <DD>Dec 25, 2016</DD>
     * </DL>
     *
     * @return the value of containsInitialVowels field
     */
    boolean isContainsInitialVowels();

    /**
     * <DL>
     * <DT>Description:</DT>
     * <DD>determine if the list of syllables is null or empty</DD>
     * <DT>Date:</DT>
     * <DD>Dec 25, 2016</DD>
     * </DL>
     *
     * @return
     *         <DL>
     *         <DT><code>true</code></DT>
     *         <DD>the list is not null or empty</DD>
     *         <DT><code>false</code></DT>
     *         <DD>otherwise</DD>
     *         </DL>
     */
    boolean isListvalid();

    /**
     * <DL>
     * <DT>Description:</DT>
     * <DD>This method is added so the SyllableListHolders can be instantiated from files independently</DD>
     * <DT>Date:</DT>
     * <DD>Jan 7, 2017</DD>
     * </DL>
     *
     * @param l_FileName
     *            the name of the file to load
     * @param syllableType
     *            the {@link com.parasoft.sa.ctp.SyllableType} of the Syllable to load
     * @throws NameGeneratorException
     *             if an error occurred while loading this SyllableListHolder
     */
    void loadListFromFile(String l_FileName, SyllableType syllableType) throws NameGeneratorException;

    /**
     * <DL>
     * <DT>Description:</DT>
     * <DD>return a random syllable from the list or null if the list is empty</DD>
     * <DT>Date:</DT>
     * <DD>Dec 25, 2016</DD>
     * </DL>
     *
     * @return
     *         <DL>
     *         <DT><code>List has elements</code></DT>
     *         <DD>return random Syllable from the list</DD>
     *         <DT><code>otherwise</code></DT>
     *         <DD>return <code>null</code></DD>
     *         </DL>
     */
    ISyllable randomSyllable();

    /**
     * <DL>
     * <DT>Description:</DT>
     * <DD>reset the syllable list and rule state. this method is normally called prior to re-populating the list</DD>
     * <DT>Date:</DT>
     * <DD>Jan 7, 2017</DD>
     * </DL>
     */
    void reset();

    /**
     * <DL>
     * <DT>Description:</DT>
     * <DD>Setter for the allowsCons property</DD>
     * <DT>Date:</DT>
     * <DD>Dec 25, 2016</DD>
     * </DL>
     *
     * @param aAllowsCons
     *            new value for the allowsCons property
     */
    void setAllowsCons(boolean aAllowsCons);

    /**
     * <DL>
     * <DT>Description:</DT>
     * <DD>Setter for the allowsVowels property</DD>
     * <DT>Date:</DT>
     * <DD>Dec 25, 2016</DD>
     * </DL>
     *
     * @param aAllowsVowels
     *            new value for the allowsVowels property
     */
    void setAllowsVowels(boolean aAllowsVowels);

    /**
     * <DL>
     * <DT>Description:</DT>
     * <DD>Setter for the containsInitialCons property</DD>
     * <DT>Date:</DT>
     * <DD>Dec 25, 2016</DD>
     * </DL>
     *
     * @param aContainsInitialCons
     *            new value for the containsInitialCons property
     */
    void setContainsInitialCons(boolean aContainsInitialCons);

    /**
     * <DL>
     * <DT>Description:</DT>
     * <DD>Setter for the containsInitialVowels property</DD>
     * <DT>Date:</DT>
     * <DD>Dec 25, 2016</DD>
     * </DL>
     *
     * @param aContainsInitialVowels
     *            new value for the containsInitialVowels property
     */
    void setContainsInitialVowels(boolean aContainsInitialVowels);

    /**
     * <DL>
     * <DT>Description:</DT>
     * <DD>Setter for the currentSyllableIndex property</DD>
     * <DT>Date:</DT>
     * <DD>Dec 25, 2016</DD>
     * </DL>
     *
     * @param aCurrentSyllableIndex
     *            new value for the currentSyllableIndex property
     */
    void setCurrentSyllableIndex(int aCurrentSyllableIndex);

    /**
     * <DL>
     * <DT>Description:</DT>
     * <DD>Setter for the syllables property</DD>
     * <DT>Date:</DT>
     * <DD>Dec 25, 2016</DD>
     * </DL>
     *
     * @param aSyllables
     *            new value for the syllables property
     */
    void setSyllables(List<? extends ISyllable> aSyllables);

    /**
     * <DL>
     * <DT>Description:</DT>
     * <DD>Setter for the type property</DD>
     * <DT>Date:</DT>
     * <DD>Dec 26, 2016</DD>
     * </DL>
     *
     * @param aSyllableType
     *            new value for the type property
     */
    void setType(SyllableType aSyllableType);

    /**
     * {@inheritDoc}
     * <DL>
     * <DT>Description:</DT>
     * <DD>return the syllable list size or 0 if it's null or empty</DD>
     * <DT>Date:</DT>
     * <DD>Dec 26, 2016</DD>
     * </DL>
     *
     * @return
     *         <DL>
     *         <DT>the number of elements in the list</DT>
     *         <DD>if the list is not null or empty</DD>
     *         <DT><code>0</code></DT>
     *         <DD>otherwise</DD>
     *         </DL>
     * @see java.util.ArrayList#size()
     */
    int size();

}
