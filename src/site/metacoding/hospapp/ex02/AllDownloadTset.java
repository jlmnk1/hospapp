package site.metacoding.hospapp.ex02;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.Gson;

// 목적 : 5653개 다운로드 받기
// 전략 : (1) 다운로드 1번 -> totalCount 확인
//          (2) totalCount 만큼 다운로드 - 파싱 - 검증(item사이즈)
public class AllDownloadTset {

    public static void main(String[] args) {
        // 1. URL 주소 만들기(끝)
        StringBuffer totalCountCheckUrl = new StringBuffer();

        totalCountCheckUrl.append("http://apis.data.go.kr/B551182/rprtHospService/getRprtHospService");
        totalCountCheckUrl.append("?serviceKey="); // 서비스 키
        totalCountCheckUrl
                .append("wJmmW29e3AEUjwLioQR22CpmqS645ep4S8TSlqtSbEsxvnkZFoNe7YG1weEWQHYZ229eNLidnI2Yt5EZ3Stv7g==");
        totalCountCheckUrl.append("&pageNo="); // 페이지 넘버
        totalCountCheckUrl.append("1");
        totalCountCheckUrl.append("&numOfRows="); // 페이지당 가져올 데이터 수
        totalCountCheckUrl.append("2"); // totalCount 체크만 할것이기 때문에 2개만 받아도 된다.
        totalCountCheckUrl.append("&_type="); // 데이터 포맷은 JSON
        totalCountCheckUrl.append("json");

        // 2. 다운로드 만들기(끝)
        try {
            URL url = new URL(totalCountCheckUrl.toString());
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            BufferedReader br = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), "utf-8"));

            StringBuffer sbtotalCountCheck = new StringBuffer();
            while (true) {
                String input = br.readLine();
                if (input == null) {
                    break;
                }
                sbtotalCountCheck.append(input);
            }
            System.out.println(sbtotalCountCheck.toString());

            // 3. 파싱
            Gson gson = new Gson();
            ResponseDto responseDto = gson.fromJson(sbtotalCountCheck.toString(),
                    ResponseDto.class);

            // 5. totalCount 담기
            int totalCount = responseDto.getResponse().getBody().getTotalCount();
            System.out.println("totalCount : " + totalCount);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
