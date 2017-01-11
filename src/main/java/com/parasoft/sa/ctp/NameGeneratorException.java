/**
 * @author nrapopor - Nick Rapoport
 * @copyright Copyright 2016 ( Dec 24, 2016 ) Nick Rapoport all rights reserved.
 */
package com.parasoft.sa.ctp;

/**
 * <DL>
 * <DT>Description:</DT>
 * <DD>and exception used by the NameGenerator to signal it's displeasure</DD>
 * <DT>Date:</DT>
 * <DD>Dec 24, 2016</DD>
 * </DL>
 *
 * @author nrapopor - Nick Rapoport
 *
 */
public class NameGeneratorException extends Exception {
    /**
     * <DL>
     * <DT>serialVersionUID</DT>
     * <DD>default value</DD>
     * </DL>
     */
    private static final long serialVersionUID = 1L;

    @SuppressWarnings("unused")
    private static org.slf4j.Logger LOG = org.slf4j.LoggerFactory.getLogger(NameGeneratorException.class);

    /**
     * <DL>
     * <DT>Description:</DT>
     * <DD>NameGeneratorException Constructor</DD>
     * <DT>Date:</DT>
     * <DD>Dec 24, 2016</DD>
     * </DL>
     */
    public NameGeneratorException() {
        super();
    }

    /**
     * <DL>
     * <DT>Description:</DT>
     * <DD>NameGeneratorException Constructor</DD>
     * <DT>Date:</DT>
     * <DD>Dec 24, 2016</DD>
     * </DL>
     *
     * @param aMessage
     */
    public NameGeneratorException(final String aMessage) {
        super(aMessage);
    }

    /**
     * <DL>
     * <DT>Description:</DT>
     * <DD>NameGeneratorException Constructor</DD>
     * <DT>Date:</DT>
     * <DD>Dec 24, 2016</DD>
     * </DL>
     *
     * @param aMessage
     * @param aCause
     */
    public NameGeneratorException(final String aMessage, final Throwable aCause) {
        super(aMessage, aCause);
    }

    /**
     * <DL>
     * <DT>Description:</DT>
     * <DD>NameGeneratorException Constructor</DD>
     * <DT>Date:</DT>
     * <DD>Dec 24, 2016</DD>
     * </DL>
     *
     * @param aMessage
     * @param aCause
     * @param aEnableSuppression
     * @param aWritableStackTrace
     */
    public NameGeneratorException(final String aMessage, final Throwable aCause, final boolean aEnableSuppression,
            final boolean aWritableStackTrace) {
        super(aMessage, aCause, aEnableSuppression, aWritableStackTrace);
    }

    /**
     * <DL>
     * <DT>Description:</DT>
     * <DD>NameGeneratorException Constructor</DD>
     * <DT>Date:</DT>
     * <DD>Dec 24, 2016</DD>
     * </DL>
     *
     * @param aCause
     */
    public NameGeneratorException(final Throwable aCause) {
        super(aCause);
    }

}
