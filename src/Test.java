
public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String url = "https://s18.postimg.org/cdaq4ytwp/image.jpg";
		String temp = url.substring(url.lastIndexOf("/") + 1 , url.length());
		System.out.println(temp);
		StringBuilder sb = new StringBuilder(temp);
		int count=1;
		sb.insert(0, "image/" + String.valueOf(count));
		
		System.out.println(sb.toString());
	}

}
