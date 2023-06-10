
package database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.DonHang;
import model.KhachHang;
import model.TacGia;

public class DonHangDAO implements DAOInterface<DonHang> {

	@Override
	public ArrayList<DonHang> selectAll() {
		ArrayList<DonHang> ketQua = new ArrayList<DonHang>();
		Connection con = JDBCUtil.getConnection();
		String sql = "SELECT * FROM donhang";
		try {
			PreparedStatement st = con.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				String maDH = rs.getString("madonhang");
				String maKH = rs.getString("makhachhang");
				String diaChiNguoiMua = rs.getString("diachinguoimua");
				String diaChiNhanHang = rs.getString("diachinguoinhan");
				String trangThai = rs.getString("trangthai");
				String hinhThucThanhToan = rs.getString("thanhtoan");
				String trangThaiThanhToan = rs.getString("trangthaithanhtoan");
				double soTienDaThanhToan = rs.getDouble("tienthanhtoan");
				double soTienConThieu = rs.getDouble("tienthieu");
				Date ngayDatHang = rs.getDate("ngaydathang");
				Date ngayGiaoHang = rs.getDate("ngaygiaohang");

				KhachHang khachHang = new KhachHangDAO()
						.selectById(new KhachHang(maKH, "", "", "", "", "", "", "", null, "", "", false));
				DonHang dh = new DonHang(maDH, khachHang, diaChiNguoiMua, diaChiNhanHang, trangThai, hinhThucThanhToan,
						trangThaiThanhToan, soTienDaThanhToan, soTienConThieu, ngayDatHang, ngayGiaoHang);

				ketQua.add(dh);
			}
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ketQua;
	}

	@Override
	public DonHang selectById(DonHang t) {
		DonHang ketQua = null;
		Connection con = JDBCUtil.getConnection();
		String sql = "SELECT * FROM donhang WHERE madonhang = ?";
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getMaDonHang()); //chi can truyen vao ma cua don hang
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				String maDH = rs.getString("madonhang");
				String maKH = rs.getString("makhachhang");
				String diaChiNguoiMua = rs.getString("diachinguoimua");
				String diaChiNhanHang = rs.getString("diachinguoinhan");
				String trangThai = rs.getString("trangthai");
				String hinhThucThanhToan = rs.getString("thanhtoan");
				String trangThaiThanhToan = rs.getString("trangthaithanhtoan");
				double soTienDaThanhToan = rs.getDouble("tienthanhtoan");
				double soTienConThieu = rs.getDouble("tienthieu");
				Date ngayDatHang = rs.getDate("ngaydathang");
				Date ngayGiaoHang = rs.getDate("ngaygiaohang");

				KhachHang khachHang = new KhachHangDAO()
						.selectById(new KhachHang(maKH, "", "", "", "", "", "", "", null, "", "", false));
				DonHang dh = new DonHang(maDH, khachHang, diaChiNguoiMua, diaChiNhanHang, trangThai, hinhThucThanhToan,
						trangThaiThanhToan, soTienDaThanhToan, soTienConThieu, ngayDatHang, ngayGiaoHang);

				ketQua = dh;
			}
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ketQua;
	}

	@Override
	public int insert(DonHang t) {
		int kq = 0;
		Connection con = JDBCUtil.getConnection();
		String sql = "INSERT INTO `donhang`(`madonhang`, `makhachhang`, `diachinguoimua`, `diachinguoinhan`,"
				+ " `trangthai`, `thanhtoan`, `trangthaithanhtoan`, `tienthanhtoan`, `tienthieu`, `ngaydathang`, `ngaygiaohang`) "
				+ " VALUES ('?','?','?','?','?','?','?','?','?','?','?')";
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getMaDonHang());
			st.setString(2, t.getKhachHang().getMaKhacHang());
			st.setString(3, t.getDiaChiMuaHang());
			st.setString(4, t.getDiaChiNhanHang());
			st.setString(5, t.getTrangThai());
			st.setString(6, t.getHinhThucThanhToan());
			st.setString(7, t.getTrangThaiThanhToan());
			st.setDouble(8, t.getSoTienDaThanhToan());
			st.setDouble(9, t.getSoTienConThieu());
			st.setDate(10, t.getNgayDatHang());
			st.setDate(11, t.getNgayGiaoHang());

			kq = st.executeUpdate();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return kq;
	}

	@Override
	public int insertAll(ArrayList<DonHang> arr) {
		int kq = 0;
		for (DonHang donHang : arr) {
			kq += this.insert(donHang);
		}
		return kq;
	}

	@Override
	public int delete(DonHang t) {
		int kq = 0;
		Connection con = JDBCUtil.getConnection();
		String sql = "DELETE FROM donhang WHERE madonhang = ?";
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getMaDonHang());
			kq = st.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return kq;
	}

	@Override
	public int deleteAll(ArrayList<DonHang> arr) {
		int kq = 0;
		for (DonHang t : arr) {
			kq += this.delete(t);
		}
		return kq;
	}

	@Override
	public int update(DonHang t) {
		int kq = 0;
		Connection con = JDBCUtil.getConnection();

		String sql = "UPDATE donhang" + " SET " + "makhachhang=?" + ", diachinguoimua=?" + ",diachinguoinhan=?"
				+ ",trangthai=?" + ",thanhtoan=?" + ",trangthaithanhtoan=?" + ",tienthanhtoan=?" + ",tienthieu=?" + ",ngaydathang=?"
				+ ",ngaygiaohang=?" + " WHERE madonhang=?";
		try {
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getKhachHang().getMaKhacHang());
			st.setString(2, t.getDiaChiMuaHang());
			st.setString(3, t.getDiaChiNhanHang());
			st.setString(4, t.getTrangThai());
			st.setString(5, t.getHinhThucThanhToan());
			st.setString(6, t.getTrangThaiThanhToan());
			st.setDouble(7, t.getSoTienDaThanhToan());
			st.setDouble(8, t.getSoTienConThieu());
			st.setDate(9, t.getNgayDatHang());
			st.setDate(10, t.getNgayGiaoHang());
			st.setString(11, t.getMaDonHang());

			kq = st.executeUpdate();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return kq;
	}
}