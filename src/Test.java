import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String exp = "<div.class='page_title'.*?>([\\s\\S]*)</div>";
		
		String input = " <div class='page_title'>嘘,别说话~夜晚的灯光很美.[30P]</div> ";
		String result;
		Pattern pattern = Pattern.compile(exp);
		Matcher matcher = pattern.matcher(input);
		while(matcher.find()){
			String input2 = matcher.group(1);
			System.out.println(input2);
			
	
		}
		
	}

}
