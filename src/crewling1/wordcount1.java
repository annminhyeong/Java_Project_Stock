package crewling1;

public class wordcount1 {
	public static void main(String[] args){

		String str = "테스트";
		String arr[] = new String[100]; //[hello] [java] [] [] [][]...[]

		int cnt[] = new int[100];  //[3]  [1]  [] [] []..[]

		int n=0;//중복되지 않는다면 n번째 데이터를 넣고 n을 증가시키고자 한다.  



		int j=0;

		String []st = str.split(" ");//[hello][hello][hello][java][hello][]..



		for(int i=0; i<st.length; i++){

			String s = st[i];



			for(j=0; j<n; j++) //중복된 단어가 있는 검사.   j번째 중복된 위치

			{

				if(s.equals(arr[j])){

					break;

				}

			}

			if(j <n){

				cnt[j]++;

			}

			if(n==0 || j == n){

				arr[n] = s;

				cnt[n] = 1;

				n++;

			}

		}



		for(int i=0; i<n; i++){

			System.out.println(arr[i] + "==>" + cnt[i]);

		}

	}

}
