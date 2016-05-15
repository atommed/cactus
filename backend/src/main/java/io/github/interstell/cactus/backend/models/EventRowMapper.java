package io.github.interstell.cactus.backend.models;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Grigoriy on 5/15/2016.
 */
public class EventRowMapper implements RowMapper<Event> {
    @Override
    public Event mapRow(ResultSet rs, int rowNum) throws SQLException {
        Event ev = new Event();
        ev.setId(rs.getInt("id"));
        ev.setName(rs.getString("name"));
        ev.setDescription(rs.getString("description"));
        ev.setFree(rs.getBoolean("free"));
        ev.setPrice(rs.getString("price"));
        ev.setDate(rs.getDate("date"));
        ev.setLat(rs.getDouble("lat"));
        ev.setLon(rs.getDouble("lon"));
        ev.setLikes(rs.getInt("likes"));
        ev.setUri(rs.getString("img"));

        return ev;
    }
}
