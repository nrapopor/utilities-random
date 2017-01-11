/**
 * @author nrapopor - Nick Rapoport
 * @copyright Copyright 2017 ( Jan 9, 2017 ) Nick Rapoport all rights reserved.
 */
package com.parasoft.sa.ctp;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

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
public class LoadConfig implements IConstants {
    private static org.slf4j.Logger LOG = org.slf4j.LoggerFactory.getLogger(LoadConfig.class);

    private static final String BUNDLE_NAME = "utilities-random"; //$NON-NLS-1$

    private static final String BUNDLE_ERROR_KEY = "%1$s: key '%2$s' is missing from the bundle %3$s using '%4$s'"; //$NON-NLS-1$

    private static final String BUNDLE_ERROR_VALUE =
        "%1$s: key '%2$s' is malformed in the bundle %3$s using '%4$s' the string was '%5$s'"; //$NON-NLS-1$

    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);

    protected static final LoadConfig instance = new LoadConfig();

    public static int getInt(final String key, final int defaultValue) {
        int res = defaultValue;
        String argStr = null;
        try {
            argStr = getString(key);
            res = Integer.parseInt(argStr);
        } catch (final NumberFormatException ex) {
            Utils.getInstance().logState(ex,
                String.format(BUNDLE_ERROR_VALUE, "getInt", key, BUNDLE_NAME, defaultValue, argStr), LOG); //$NON-NLS-1$

        }
        return res;
    }

    public static String getString(final String key) {
        return getString(key, "!" + key + "!"); //$NON-NLS-1$ //$NON-NLS-2$
    }

    public static String getString(final String key, final String defaultValue) {
        try {
            return RESOURCE_BUNDLE.getString(key);
        } catch (final MissingResourceException ex) {
            Utils.getInstance().logState(ex,
                String.format(BUNDLE_ERROR_KEY, "getString", key, BUNDLE_NAME, "" + defaultValue), LOG); //$NON-NLS-1$ //$NON-NLS-2$
            return defaultValue;
        }
    }

    private LoadConfig() {
        // nothing to do
    }

}
