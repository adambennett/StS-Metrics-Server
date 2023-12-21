package DuelistMetrics.Server.models.dto;

public record RunLogDTO(Long run_id,
                        Integer ascension,
                        Integer challenge,
                        String characterName,
                        String deck,
                        Integer floor,
                        String host,
                        Boolean kaiba,
                        String killedBy,
                        String time,
                        Boolean victory,
                        String country,
                        String filterDate,
                        String language,
                        String uuid,
                        String utcTime) {
    public RunLogDTO(Long run_id,
                     Integer ascension,
                     Integer challenge,
                     String characterName,
                     String deck,
                     Integer floor,
                     String host,
                     Boolean kaiba,
                     String killedBy,
                     String time,
                     Boolean victory,
                     String country,
                     String filterDate,
                     String language) {
        this(run_id, ascension, challenge, characterName, deck, floor, host, kaiba, killedBy, time, victory, country, filterDate, language, null, null);
    }

    public RunLogDTO(RunLogDTO w, String uuid, String utcTime) {
        this(w.run_id(), w.ascension(), w.challenge(), w.characterName(), w.deck(), w.floor(), w.host(), w.kaiba(), w.killedBy(), w.time(), w.victory(), w.country(), w.filterDate(), w.language(), uuid, utcTime);
    }
}
