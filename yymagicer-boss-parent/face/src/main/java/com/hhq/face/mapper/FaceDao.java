package com.hhq.face.mapper;

import com.hhq.face.po.FaceIndex;
import com.hhq.face.sqlite.RowMapper;
import com.hhq.face.sqlite.SqliteUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @Author Sugar
 * @Version 2019/4/22 17:14
 */
public class FaceDao {
    public static String findKeyByIndex(int index) {
        try {
            return SqliteUtils.queryForString("select \"key\" from face_index where \"index\"=" + index);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean saveOrUpdate(FaceIndex index) {
        try {
            SqliteUtils.executeUpdate("INSERT OR REPLACE INTO face_img (\"key\",\"img_data\",\"width\",\"height\",\"channel\") VALUES (?,?,?,?,?)", new Object[]{index
                    .getKey(), index.getImgData(), index.getWidth(), index.getHeight(), index.getChannel()});
            SqliteUtils.executeUpdate("INSERT OR REPLACE INTO face_index (\"index\",\"key\") VALUES (?,?)", new Object[]{index
                    .getIndex(), index.getKey()});
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean saveOrUpdateIndex(FaceIndex index) {
        try {
            SqliteUtils.executeUpdate("INSERT OR REPLACE INTO face_index (\"index\",\"key\") VALUES (?,?)", new Object[]{index
                    .getIndex(), index.getKey()});
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean clearIndex() {
        try {
            SqliteUtils.executeUpdate("DELETE FROM face_index");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static List<FaceIndex> findFaceImgs(int pageNo, int pageSize) {
        String sql = "select \"key\",\"img_data\",\"width\",\"height\",\"channel\" from face_img " +
                " limit " + pageNo * pageSize + "," + pageSize;
        try {
            return SqliteUtils.queryForList(sql, new RowMapper<FaceIndex>() {
                @Override
                public FaceIndex mapRow(ResultSet rs) throws SQLException {
                    FaceIndex face = new FaceIndex();
                    face.setKey(rs.getString("key"));
                    face.setImgData(rs.getBytes("img_data"));
                    face.setWidth(rs.getInt("width"));
                    face.setHeight(rs.getInt("height"));
                    face.setChannel(rs.getInt("channel"));
                    return face;
                }
            });
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean deleteFaceImg(String... keys) {
        StringBuilder in = new StringBuilder();
        for (int i = 0; i < keys.length; i++) {
            if (i > 0) {
                in.append(",");
            }
            in.append("?");
        }
        try {
            SqliteUtils.executeUpdate("DELETE FROM face_img where key in ("+in+")", keys);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static boolean clearImg() {
        try {
            SqliteUtils.executeUpdate("DELETE FROM face_img");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
