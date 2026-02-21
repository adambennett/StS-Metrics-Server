package DuelistMetrics.Server.models.tierScore;

import java.util.Date;

public interface GeneralScoringCard {

    default String printCondensed() {
        return getCard_name() + ": " + getOverall_score();
    }

    String getCard_id();

    String getPool_name();

    GeneralScoringCard generate(String card_id, String pool_name);

    String getCard_name();

    void setCard_name(String card_name);

    Date getLastUpdated();

    void setLastUpdated(Date lastUpdated);

    float getAct0_delta();

    void setAct0_delta(float act0_delta);

    float getAct1_delta();

    void setAct1_delta(float act1_delta);

    float getAct2_delta();

    void setAct2_delta(float act2_delta);

    float getAct3_delta();

    void setAct3_delta(float act3_delta);

    float getAct0_win_rate();

    void setAct0_win_rate(float act0_win_rate);

    float getAct1_win_rate();

    void setAct1_win_rate(float act1_win_rate);

    float getAct2_win_rate();

    void setAct2_win_rate(float act2_win_rate);

    float getAct3_win_rate();

    void setAct3_win_rate(float act3_win_rate);

    int getAct0_wins();

    void setAct0_wins(int act0_wins);

    int getAct1_wins();

    void setAct1_wins(int act1_wins);

    int getAct2_wins();

    void setAct2_wins(int act2_wins);

    int getAct3_wins();

    void setAct3_wins(int act3_wins);

    int getAct0_losses();

    void setAct0_losses(int act0_losses);

    int getAct1_losses();

    void setAct1_losses(int act1_losses);

    int getAct2_losses();

    void setAct2_losses(int act2_losses);

    int getAct3_losses();

    void setAct3_losses(int act3_losses);

    int getAct0_score();

    void setAct0_score(int act0_score);

    int getAct1_score();

    void setAct1_score(int act1_score);

    int getAct2_score();

    void setAct2_score(int act2_score);

    int getAct3_score();

    void setAct3_score(int act3_score);

    int getOverall_score();

    void setOverall_score(int overall_score);

    int getPosition();

    void setPosition(int position);

    int getA0_position();

    void setA0_position(int a0_position);

    int getA1_position();

    void setA1_position(int a1_position);

    int getA2_position();

    void setA2_position(int a2_position);

    int getA3_position();

    void setA3_position(int a3_position);

    float getPercentile();

    void setPercentile(float percentile);

    float getA0_percentile();

    void setA0_percentile(float a0_percentile);

    float getA1_percentile();

    void setA1_percentile(float a1_percentile);

    float getA2_percentile();

    void setA2_percentile(float a2_percentile);

    float getA3_percentile();

    void setA3_percentile(float a3_percentile);

    void multOverall(float multi);

    void multA0(float multi);

    void multA1(float multi);

    void multA2(float multi);

    void multA3(float multi);

    void inc100();
}
