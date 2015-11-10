package reserve.chart;

import java.io.Reader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import reserve.reserve.ReserveDTO;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;


	@Controller
public class ChartBean {

		public static Reader reader;
		public static SqlMapClient sqlMapper;

		public ChartBean() throws Exception{
			reader=Resources.getResourceAsReader("sqlMapConfig.xml");
			sqlMapper=SqlMapClientBuilder.buildSqlMapClient(reader);
			reader.close();
		}
		
		@RequestMapping("pieChart.do")
		public ModelAndView pieChart(){
			
			String result="";
			PieDTO pie=new PieDTO(); 
			List<PieDTO> pie_list=new ArrayList<PieDTO>();
			
			Date today = getDate(new Date());
			Date end_today=afterOneDay(today);
			
			
			Calendar c = Calendar.getInstance();
			c.add(Calendar.DATE, (Calendar.MONDAY - c.get(Calendar.DAY_OF_WEEK)));
			Date week=new Date(c.getTimeInMillis());
			week=getDate(week);
			
			
			c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH),1);
			Date month=new Date(c.getTimeInMillis());
			month=getDate(month);
			System.out.println("today: "+c.getTime());
			System.out.println("년: "+c.get(Calendar.YEAR));
			System.out.println("월: "+c.get(Calendar.MONTH));
			System.out.println("이번달 1일:"+month);
			System.out.println("이번달 1일:"+end_today);
			
			pie.setToday(end_today);
			pie.setThis_month_date(month);
			try {
				pie_list=(List<PieDTO>) sqlMapper.queryForList("findToReserveCount_regdate",pie);
				System.out.println("-----------------");
				for(PieDTO p : pie_list){
					result+=" [ '"+p.getPart_name()+"' , "+p.getPartnum()+" ] ,";
				
				}
				
				result=result.substring(1, result.length()-1 );
				result="[ "+result+" ]";
				
			} catch (Exception e) {
			}

			ModelAndView mv= new ModelAndView();
			mv.addObject("pie_list",result);
			mv.addObject("BODY_PATH","/chart/pieChart.jsp");
			mv.setViewName("/main/main.jsp");
			return mv;
		}
		@RequestMapping("barChart.do")
		public ModelAndView barChart(){
			String result="";
			String part_name="";
			PieDTO pie=new PieDTO(); 
			List<PieDTO> pie_list=new ArrayList<PieDTO>();
			
			Date today = getDate(new Date());
			Date end_today=afterOneDay(today);
			
			
			Calendar c = Calendar.getInstance();
			c.add(Calendar.DATE, (Calendar.MONDAY - c.get(Calendar.DAY_OF_WEEK)));
			Date week=new Date(c.getTimeInMillis());
			week=getDate(week);
			
			
			c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH),1);
			Date month=new Date(c.getTimeInMillis());
			month=getDate(month);
			System.out.println("today: "+c.getTime());
			System.out.println("년: "+c.get(Calendar.YEAR));
			System.out.println("월: "+c.get(Calendar.MONTH));
			System.out.println("이번달 1일:"+month);
			System.out.println("이번달 1일:"+end_today);
			
			pie.setToday(end_today);
			pie.setThis_month_date(month);
			try {
				pie_list=(List<PieDTO>) sqlMapper.queryForList("findToReserveCount_regdate",pie);
				System.out.println("-----------------");
				for(PieDTO p : pie_list){
					part_name+=" '"+p.getPart_name()+"' ,";
					result+=" "+p.getPartnum()+" ,";
				
				}
				
				System.out.println("result: "+result);
				System.out.println("result 콤마 빼기: "+result.substring(1, result.length()-1 ));
				
				result=result.substring(1, result.length()-1 );
				part_name=part_name.substring(1, part_name.length()-1 );

				result="[ "+result+" ]";
				part_name="[ "+part_name+" ]";
				
				
				System.out.println("result완성: "+result);
			} catch (Exception e) {
			}

			ModelAndView mv= new ModelAndView();
			mv.addObject("result",result);
			mv.addObject("part_name",part_name);
			mv.addObject("BODY_PATH","/chart/barChart.jsp");
			mv.setViewName("/main/main.jsp");
			return mv;
		}
		
		
		@RequestMapping("barMonthChart.do")
		public ModelAndView barMonthChart(){
			String result="";
			String result2="";
			List<PieDTO> pie_list2=new ArrayList<PieDTO>();
			
			
				try {
					
					pie_list2=sqlMapper.queryForList("findToReserveCount_total");
					
				} catch (Exception e) {
					System.out.println("쿼리실패");
				}
				try {
				System.out.println("-----------------");
				for(PieDTO p : pie_list2){

					result+=" '"+returnDateString(p.getReserve_date())+"' ,";
				
				}
				
				int total=0;
				for(PieDTO p : pie_list2){
					total+=p.getReservenum();
					result2+=" "+total+" ,";
				
				}
				
				System.out.println("result: "+result);
				System.out.println("result 콤마 빼기: "+result.substring(1, result.length()-1 ));
				
				result=result.substring(1, result.length()-1 );
				result2=result2.substring(1, result2.length()-1 );
				result="[ "+result+" ]";
				result2="[ "+result2+" ]";
				
				System.out.println("result완성: "+result);
			} catch (Exception e) {
				System.out.println("실패");
			}

			ModelAndView mv= new ModelAndView();
			mv.addObject("total_list",result);
			mv.addObject("total_list2",result2);
			mv.addObject("BODY_PATH","/chart/barMonthChart.jsp");
			mv.setViewName("/main/main.jsp");
			return mv;
		}
		@RequestMapping("chartMain.do")
		public ModelAndView chartMain(){
			
			ModelAndView mv= new ModelAndView();
			

			mv.setViewName("/barMonthChart.do");
			return mv;
		}
		
		
		//날짜 설정하는 메소드 
		public static Date getDate(Date thisDate) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(thisDate);
			cal.set(cal.get(cal.YEAR),cal.get(cal.MONTH),cal.get(cal.DATE), 0, 0, 0);
			cal.set(Calendar.MILLISECOND, 0);
			System.out.println("날짜는"+cal.getTime());
			return cal.getTime();
		}
		
		public Date afterOneDay(Date d){
			//d 기준날짜를 입력한다.
			long dd=d.getTime();
			//밀리세컨드*60초*60분*24시간==하루
			return new Date(dd+1000*60*60*24);
		}
		
		public String returnDateString(Date date){
			  SimpleDateFormat df = new SimpleDateFormat("yyyy/ MM/ dd");
		        
//		        Date date1 = new Date();
		        String today = df.format(date);
		        System.out.println(today);
		        
		        return today;
			
		}
}
