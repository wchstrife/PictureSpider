import java.awt.Image;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.security.interfaces.RSAMultiPrimePrivateCrtKey;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.net.URL;
import java.net.URLConnection;

public class CatchPicture {
	
	private static final String URL = "http://www.snhtl.com/html/article/index4012.html";
	
	private static final String ECODING = "UTF-8";
	
	private static final String IMAGE_REG = "<img.*src=(.*?)[^>]*?>";
	
	private static final String IMASRC_REG = "http:\"?(.*?)(\"|>|\\s+)";
	
	public static void main(String[] args) {
		try{
		CatchPicture catchPicture = new CatchPicture();
		String HTML = catchPicture.getHTML(URL);
	
		 //获取图片标签  
        List<String> imgUrl = catchPicture.getImageUrl(HTML);  
        //获取图片src地址  
        List<String> imgSrc = catchPicture.getImageSrc(imgUrl);  
        //下载图片  
        catchPicture.Download(imgSrc);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取HTML内容
	 * @param url
	 * @return
	 * @throws Exception
	 */
	private String getHTML(String url) throws Exception{
		URL uri = new URL(url);
		URLConnection connection = uri.openConnection();
		InputStream inputStream = connection.getInputStream();
		byte[] buf = new byte[1024];
		int length = 0;
		StringBuffer sb = new StringBuffer();
		
		while ((length = inputStream.read(buf, 0, buf.length)) > 0) {  
            sb.append(new String(buf, ECODING));  
        }  
        inputStream.close();  
        return sb.toString();
	}
	
	/*** 
     * 获取ImageUrl地址 
     *  
     * @param HTML 
     * @return 
     */  
    private List<String> getImageUrl(String HTML) {  
        Matcher matcher = Pattern.compile(IMAGE_REG).matcher(HTML);  
        List<String> listImgUrl = new ArrayList<String>();  
        while (matcher.find()) {  
            listImgUrl.add(matcher.group());
        }  
        
        return listImgUrl;  
    }  
    
    /*** 
     * 获取ImageSrc地址 
     *  
     * @param listImageUrl 
     * @return 
     */  
    private List<String> getImageSrc(List<String> listImageUrl) {  
        List<String> listImgSrc = new ArrayList<String>();  
        for (String image : listImageUrl) {  
            Matcher matcher = Pattern.compile(IMASRC_REG).matcher(image);  
            while (matcher.find()) {  
                listImgSrc.add(matcher.group().substring(0, matcher.group().length() - 1).replaceAll("postimg", "pixxxels"));
               
            }  
        }  
        return listImgSrc;  
    }  
    
    private void Download(List<String> listImgSrc) {
    	int count = 1;
        try {  
            for (String url : listImgSrc) {
            	StringBuilder sBuilder = new StringBuilder(url.substring(url.lastIndexOf("/") + 1, url.length()));
            	count++;
            	sBuilder.insert(0, "image/"+String.valueOf(count));
                String imageName = sBuilder.toString();
                URL uri = new URL(url);  
                InputStream in = uri.openStream();  
                FileOutputStream fo = new FileOutputStream(new File(imageName));  
                byte[] buf = new byte[1024];  
                int length = 0;  
                System.out.println("开始下载:" + url);  
                while ((length = in.read(buf, 0, buf.length)) != -1) {  
                    fo.write(buf, 0, length);  
                }  
                in.close();  
                fo.close();  
                System.out.println(imageName + "下载完成");  
            }  
        } catch (Exception e) {
        	e.printStackTrace();
            System.out.println("下载失败");  
        }  
        System.out.println("全部任务下载完成");
    } 
}
