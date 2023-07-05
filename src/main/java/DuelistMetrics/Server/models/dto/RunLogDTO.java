package DuelistMetrics.Server.models.dto;

import DuelistMetrics.Server.models.RunLog;

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
                        String language) {
    public RunLogDTO(RunLog r) {
        this(r.getRun_id(), r.getAscension(), r.getChallenge(), r.getCharacterName(), r.getDeck(), r.getFloor(), r.getHost(), r.getKaiba(), r.getKilledBy(), r.getTime(), r.getVictory(), r.getCountry(), r.getFilterDate(), r.getLanguage());
    }
}
