package com.drest.storage.mysql.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.drest.app.model.SingleColumn;

public class SingleColumnMapper implements RowMapper<SingleColumn> {

	@Override
	public SingleColumn mapRow(ResultSet rs, int rowNum) throws SQLException {
		SingleColumn col = new SingleColumn();
		col.setCol(rs.getInt("count"));
		return col;
	}
}
