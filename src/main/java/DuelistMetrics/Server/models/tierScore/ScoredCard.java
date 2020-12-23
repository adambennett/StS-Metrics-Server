package DuelistMetrics.Server.models.tierScore;

import javax.persistence.*;
import java.util.*;

@Entity
@IdClass(ScoredCardKey.class)
public class ScoredCard {

    @Id
    public String card_id;

    @Id
    public String pool_name;

    private float act0_delta;
    private float act1_delta;
    private float act2_delta;
    private float act3_delta;
    private float act0_win_rate;
    private float act1_win_rate;
    private float act2_win_rate;
    private float act3_win_rate;
    private int act0_wins;
    private int act1_wins;
    private int act2_wins;
    private int act3_wins;
    private int act0_losses;
    private int act1_losses;
    private int act2_losses;
    private int act3_losses;
    private int act0_score;
    private int act1_score;
    private int act2_score;
    private int act3_score;
    private int overall_score;
    private int position;
    private int a0_position;
    private int a1_position;
    private int a2_position;
    private int a3_position;
    private float percentile;
    private float a0_percentile;
    private float a1_percentile;
    private float a2_percentile;
    private float a3_percentile;

    public ScoredCard() {}

    public ScoredCard(String card_id, String pool_name) {
        this.card_id = card_id;
        this.pool_name = pool_name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ScoredCard)) return false;
        ScoredCard that = (ScoredCard) o;
        return card_id.equals(that.card_id) && pool_name.equals(that.pool_name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(card_id, pool_name);
    }

    public String getCard_id() {
        return card_id;
    }

    public void setCard_id(String card_id) {
        this.card_id = card_id;
    }

    public String getPool_name() {
        return pool_name;
    }

    public void setPool_name(String pool_name) {
        this.pool_name = pool_name;
    }

    public float getAct0_delta() {
        return act0_delta;
    }

    public void setAct0_delta(float act0_delta) {
        this.act0_delta = act0_delta;
    }

    public float getAct1_delta() {
        return act1_delta;
    }

    public void setAct1_delta(float act1_delta) {
        this.act1_delta = act1_delta;
    }

    public float getAct2_delta() {
        return act2_delta;
    }

    public void setAct2_delta(float act2_delta) {
        this.act2_delta = act2_delta;
    }

    public float getAct3_delta() {
        return act3_delta;
    }

    public void setAct3_delta(float act3_delta) {
        this.act3_delta = act3_delta;
    }

    public float getAct0_win_rate() {
        return act0_win_rate;
    }

    public void setAct0_win_rate(float act0_win_rate) {
        this.act0_win_rate = act0_win_rate;
    }

    public float getAct1_win_rate() {
        return act1_win_rate;
    }

    public void setAct1_win_rate(float act1_win_rate) {
        this.act1_win_rate = act1_win_rate;
    }

    public float getAct2_win_rate() {
        return act2_win_rate;
    }

    public void setAct2_win_rate(float act2_win_rate) {
        this.act2_win_rate = act2_win_rate;
    }

    public float getAct3_win_rate() {
        return act3_win_rate;
    }

    public void setAct3_win_rate(float act3_win_rate) {
        this.act3_win_rate = act3_win_rate;
    }

    public int getAct0_wins() {
        return act0_wins;
    }

    public void setAct0_wins(int act0_wins) {
        this.act0_wins = act0_wins;
    }

    public int getAct1_wins() {
        return act1_wins;
    }

    public void setAct1_wins(int act1_wins) {
        this.act1_wins = act1_wins;
    }

    public int getAct2_wins() {
        return act2_wins;
    }

    public void setAct2_wins(int act2_wins) {
        this.act2_wins = act2_wins;
    }

    public int getAct3_wins() {
        return act3_wins;
    }

    public void setAct3_wins(int act3_wins) {
        this.act3_wins = act3_wins;
    }

    public int getAct0_losses() {
        return act0_losses;
    }

    public void setAct0_losses(int act0_losses) {
        this.act0_losses = act0_losses;
    }

    public int getAct1_losses() {
        return act1_losses;
    }

    public void setAct1_losses(int act1_losses) {
        this.act1_losses = act1_losses;
    }

    public int getAct2_losses() {
        return act2_losses;
    }

    public void setAct2_losses(int act2_losses) {
        this.act2_losses = act2_losses;
    }

    public int getAct3_losses() {
        return act3_losses;
    }

    public void setAct3_losses(int act3_losses) {
        this.act3_losses = act3_losses;
    }

    public int getAct0_score() {
        return act0_score;
    }

    public void setAct0_score(int act0_score) {
        this.act0_score = act0_score;
    }

    public int getAct1_score() {
        return act1_score;
    }

    public void setAct1_score(int act1_score) {
        this.act1_score = act1_score;
    }

    public int getAct2_score() {
        return act2_score;
    }

    public void setAct2_score(int act2_score) {
        this.act2_score = act2_score;
    }

    public int getAct3_score() {
        return act3_score;
    }

    public void setAct3_score(int act3_score) {
        this.act3_score = act3_score;
    }

    public int getOverall_score() {
        return overall_score;
    }

    public void setOverall_score(int overall_score) {
        this.overall_score = overall_score;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getA0_position() {
        return a0_position;
    }

    public void setA0_position(int a0_position) {
        this.a0_position = a0_position;
    }

    public int getA1_position() {
        return a1_position;
    }

    public void setA1_position(int a1_position) {
        this.a1_position = a1_position;
    }

    public int getA2_position() {
        return a2_position;
    }

    public void setA2_position(int a2_position) {
        this.a2_position = a2_position;
    }

    public int getA3_position() {
        return a3_position;
    }

    public void setA3_position(int a3_position) {
        this.a3_position = a3_position;
    }

    public float getPercentile() {
        return percentile;
    }

    public void setPercentile(float percentile) {
        this.percentile = percentile;
    }

    public float getA0_percentile() {
        return a0_percentile;
    }

    public void setA0_percentile(float a0_percentile) {
        this.a0_percentile = a0_percentile;
    }

    public float getA1_percentile() {
        return a1_percentile;
    }

    public void setA1_percentile(float a1_percentile) {
        this.a1_percentile = a1_percentile;
    }

    public float getA2_percentile() {
        return a2_percentile;
    }

    public void setA2_percentile(float a2_percentile) {
        this.a2_percentile = a2_percentile;
    }

    public float getA3_percentile() {
        return a3_percentile;
    }

    public void setA3_percentile(float a3_percentile) {
        this.a3_percentile = a3_percentile;
    }

    public void multOverall(float multi) {
        this.overall_score *= multi;
    }

    public void multA0(float multi) {
        this.act0_score *= multi;
    }

    public void multA1(float multi) {
        this.act1_score *= multi;
    }

    public void multA2(float multi) {
        this.act2_score *= multi;
    }

    public void multA3(float multi) {
        this.act3_score *= multi;
    }

    public void inc100() {
        this.overall_score += 100;
        this.act0_score += 100;
        this.act1_score += 100;
        this.act2_score += 100;
        this.act3_score += 100;
    }
}
