package com.taofang.webapi.model;

public class Wordstatistics {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wordstatistics.WordStatisticsID
     *
     * @mbggenerated Sat May 14 21:45:19 CST 2016
     */
    private Long wordstatisticsid;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wordstatistics.SearchCount
     *
     * @mbggenerated Sat May 14 21:45:19 CST 2016
     */
    private Long searchcount;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wordstatistics.WordHashCode
     *
     * @mbggenerated Sat May 14 21:45:19 CST 2016
     */
    private Long wordhashcode;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column wordstatistics.Word
     *
     * @mbggenerated Sat May 14 21:45:19 CST 2016
     */
    private String word;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wordstatistics.WordStatisticsID
     *
     * @return the value of wordstatistics.WordStatisticsID
     *
     * @mbggenerated Sat May 14 21:45:19 CST 2016
     */
    public Long getWordstatisticsid() {
        return wordstatisticsid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wordstatistics.WordStatisticsID
     *
     * @param wordstatisticsid the value for wordstatistics.WordStatisticsID
     *
     * @mbggenerated Sat May 14 21:45:19 CST 2016
     */
    public void setWordstatisticsid(Long wordstatisticsid) {
        this.wordstatisticsid = wordstatisticsid;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wordstatistics.SearchCount
     *
     * @return the value of wordstatistics.SearchCount
     *
     * @mbggenerated Sat May 14 21:45:19 CST 2016
     */
    public Long getSearchcount() {
        return searchcount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wordstatistics.SearchCount
     *
     * @param searchcount the value for wordstatistics.SearchCount
     *
     * @mbggenerated Sat May 14 21:45:19 CST 2016
     */
    public void setSearchcount(Long searchcount) {
        this.searchcount = searchcount;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wordstatistics.WordHashCode
     *
     * @return the value of wordstatistics.WordHashCode
     *
     * @mbggenerated Sat May 14 21:45:19 CST 2016
     */
    public Long getWordhashcode() {
        return wordhashcode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wordstatistics.WordHashCode
     *
     * @param wordhashcode the value for wordstatistics.WordHashCode
     *
     * @mbggenerated Sat May 14 21:45:19 CST 2016
     */
    public void setWordhashcode(Long wordhashcode) {
        this.wordhashcode = wordhashcode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column wordstatistics.Word
     *
     * @return the value of wordstatistics.Word
     *
     * @mbggenerated Sat May 14 21:45:19 CST 2016
     */
    public String getWord() {
        return word;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column wordstatistics.Word
     *
     * @param word the value for wordstatistics.Word
     *
     * @mbggenerated Sat May 14 21:45:19 CST 2016
     */
    public void setWord(String word) {
        this.word = word == null ? null : word.trim();
    }
}