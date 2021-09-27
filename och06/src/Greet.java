

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.GregorianCalendar;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Greet
 */
@WebServlet("/Greet")
public class Greet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    PrintWriter log;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Greet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		try {
			System.out.println("init...");
			log = new PrintWriter(new FileWriter("c:/log/log.txt",true));	//파일에 저장 : 일생에 단 한번 수행
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("헐~~");
		}
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		System.out.println("destroy...");
		if (log != null) {
			log.close();
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
		//get, post는 할때마다 새로
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("doget...");
		String name = request.getParameter("name");
		String msg = name + "님 반가워요\r\n";
		GregorianCalendar gc = new GregorianCalendar();
		String date = String.format("%TF %TT\r\n", gc, gc);	// TF: 날짜, TT: 시간
		log.print(date + msg);	//로그 파일에 계속 쌓이고
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println("<html><body><h2>인사</h2>" +msg);
		out.println("</body></html>");
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
