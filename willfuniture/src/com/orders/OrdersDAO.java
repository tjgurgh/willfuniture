package com.orders;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.member.MemberDTO;


public class OrdersDAO {
	
	private Connection conn;//필수
	
	//의존성 주입
	public OrdersDAO(Connection conn) {
		this.conn = conn;
	}
	
	

		// orderNo 생성 (랜덤)
		public String getOrderNo() {
			
			Random rd = new Random();
			
			int[] n = new int[16];

			for(int i=0;i<n.length;i++) {
				n[i] = rd.nextInt(10);
			}

			String orderNo = String.format("%d%d%d%d-%d%d%d%d-%d%d%d%d-%d%d%d%d", 
					n[0],n[1],n[2],n[3]
							,n[4],n[5],n[6],n[7]
									,n[8],n[9],n[10],n[11]
											,n[12],n[13],n[14],n[15]);
			
			
			return orderNo;
		}

		public int insertData(OrdersDTO oDto) {

			int result = 0;

			PreparedStatement pstmt = null;
			String sql;

			try {

				sql = "insert into orders (orderNo,userId,productNo,productName,price,";
				sql+= "orderQuantity,options,addr,addrDetail,progress) ";
				sql+= "values (?,?,?,?,?,?,?,?,?,?)";

				pstmt = conn.prepareStatement(sql);

				pstmt.setString(1, oDto.getOrderNo());
				pstmt.setString(2, oDto.getUserId());
				pstmt.setInt(3, oDto.getProductNo());
				pstmt.setString(4, oDto.getProductName());
				pstmt.setInt(5, oDto.getPrice());
				pstmt.setInt(6, oDto.getOrderQuantity());
				pstmt.setString(7, oDto.getOptions());
				pstmt.setString(8, oDto.getAddr());
				pstmt.setString(9, oDto.getAddrDetail());
				pstmt.setString(10, oDto.getProgress());

				result = pstmt.executeUpdate();

				pstmt.close();

			} catch (Exception e) {
				System.out.println(e.toString());
			}

			return result;
		}

	
	
		public List<OrdersDTO> getList(String userId){

			List<OrdersDTO> lists = new ArrayList<OrdersDTO>();
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql;

			try {

				sql = "select orderno,userid,productno,productname,price,orderquantity,options,addr,addrdetail,progress ";
				sql+= "from orders where userid=?";

				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, userId);
				rs = pstmt.executeQuery();

				while(rs.next()) {

					OrdersDTO dto = new OrdersDTO();

					dto.setOrderNo(rs.getString("orderNo"));
					dto.setUserId(rs.getString("userId"));
					dto.setProductNo(rs.getInt("productNo"));
					dto.setProductName(rs.getString("productName"));
					dto.setPrice(rs.getInt("price"));
					dto.setOrderQuantity(rs.getInt("orderQuantity"));
					dto.setOptions(rs.getString("options"));
					dto.setAddr(rs.getString("addr"));
					dto.setAddrDetail(rs.getString("addrDetail"));
					dto.setProgress(rs.getString("progress"));

					lists.add(dto);

					

				}
				pstmt.close();
				rs.close();

			} catch (Exception e) {
				System.out.println(e.toString());
			}

			return lists;

		}

	
		public OrdersDTO getReadData(String hak) {

			OrdersDTO dto = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql;

			try {


				sql = "select orderno,userid,productno,productname,price,orderquantity,options,addr,addrdetail,progress ";
				sql+= "from orders";

				pstmt = conn.prepareStatement(sql);

				pstmt.setString(1, hak);

				rs = pstmt.executeQuery();

				if(rs.next()) {

					dto = new OrdersDTO();


					dto.setOrderNo(rs.getString("orderNo"));
					dto.setUserId(rs.getString("userId"));
					dto.setProductNo(rs.getInt("productNo"));
					dto.setProductName(rs.getString("productName"));
					dto.setPrice(rs.getInt("price"));
					dto.setOrderQuantity(rs.getInt("orderQuantity"));
					dto.setOptions(rs.getString("options"));
					dto.setAddr(rs.getString("addr"));
					dto.setAddrDetail(rs.getString("addrDetail"));
					dto.setProgress(rs.getString("progress"));
				}


				rs.close();
				pstmt.close();



			} catch (Exception e) {
				System.out.println(e.toString());
			}


			return dto;

		}


	}
