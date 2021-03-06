package net.sf.odinms.tools;

public class KoreanDateUtil {
private static final int ITEM_YEAR2000 = -1085019342;
    private static final long REAL_YEAR2000 = 946681229830L;
//    private final static long FT_UT_OFFSET2 = 116444484000000000L; // PDT
private static final long FT_UT_OFFSET2 = 116444448000000000L; // PST
    private static final long FT_UT_OFFSET = 116444736000000000L; // 100 nsseconds from 1/1/1601 -> 1/1/1970

    /**
     * Dummy constructor for static classes.
     */
    private KoreanDateUtil() {
    }

    /**
     * Converts a Unix Timestamp into File Time
     *
     * @param realTimestamp The actual timestamp in milliseconds.
     * @return A 64-bit long giving a filetime timestamp
     */
    public static long getTempBanTimestamp(long realTimestamp) {
        // long time = (realTimestamp / 1000);//seconds
        return ((realTimestamp * 10000) + FT_UT_OFFSET);
    }

    /**
     * Gets a timestamp for item expiration.
     *
     * @param realTimestamp The actual timestamp in milliseconds.
     * @return The Korean timestamp for the real timestamp.
     */
    public static int getItemTimestamp(long realTimestamp) {
        int time = (int) ((realTimestamp - REAL_YEAR2000) / 1000 / 60); // convert to minutes
        return (int) (time * 35.762787) + ITEM_YEAR2000;
    }

    /**
     * Gets a timestamp for quest repetition.
     *
     * @param realTimestamp The actual timestamp in milliseconds.
     * @return The timestamp
     */

    public static long getQuestTimestamp(long realTimestamp) {
        long time = (realTimestamp / 1000); // convert to seconds
        return ((time * 10000000) + FT_UT_OFFSET2);
    }
}