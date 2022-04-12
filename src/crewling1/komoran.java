package crewling1;

import java.util.ArrayList;
import java.util.List;
import kr.co.shineware.nlp.komoran.constant.DEFAULT_MODEL;
import kr.co.shineware.nlp.komoran.core.Komoran;
import kr.co.shineware.nlp.komoran.model.Token;

public class komoran {

	public static void main(String[] args) {

		String word = "[서울경제] 12일 오전 9시 30분 현재 코스피는 전일 대비 61.54p(-2.83) 하락한 2115.24로, 39(매도):61(매수)의 매수우위를 기록 중이다. 매수비율 매수잔량 잔량합계*100, 매수우위 매수비율 매도비율";
		Komoran komoran = new Komoran(DEFAULT_MODEL.FULL);
		List<Token> tokens = komoran.analyze(word).getTokenList();

		int cnt[] = new int[100];
		for (int i = 0; i < tokens.size(); i++) {

			System.out.println(tokens.get(i).getMorph());

		}
	}

}
