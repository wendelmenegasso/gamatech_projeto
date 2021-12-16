//package br.com.gama.tech.projeto.homologacao.controller;
//
//import java.io.BufferedReader;
//import java.io.FileReader;
//import java.io.IOException;
//import java.io.PrintWriter;
//
//import javax.servlet.ServletConfig;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//
//@WebServlet("/sacar")
//public class WebServletHome extends HttpServlet {
//	 public WebServletHome() {
//	        super();
//	        // TODO Auto-generated constructor stub
//	    }
//	 protected void service(HttpServletRequest request,
//	          HttpServletResponse response) throws ServletException, IOException {
//		 doGet(request, response);
//	 }
//	 protected void doGet(HttpServletRequest request,
//	          HttpServletResponse response) throws ServletException, IOException {
//
//		 doPost(request,response);
//	      
//	  }
//	protected void doPost(HttpServletRequest request,
//	          HttpServletResponse response) throws ServletException, IOException {
//
//	      response.setContentType("text/html");
//	      PrintWriter out = response.getWriter();
//	      String html = "";
//			String arquivo = "../meuprojeto/src/main/resources/webapps/WEB-INF/jsp/index.html";
//			try {
//				BufferedReader br = new BufferedReader(new FileReader(arquivo));
//
//				while (br.ready()) {
//					html += br.readLine();
//				}
//				br.close();
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			};
//			out.print(html);
//	      out.close();
//	  }
//}
