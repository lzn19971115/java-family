package com.huawei;

import java.util.*;

/**
 * 牛客华为机试，链接：https://www.nowcoder.com/exam/oj/ta?tpId=37
 */
public class Exam {
	public static String[] ones = new String[]{"zero","one","two","three","four","five","six","seven","eight","nine"};
	public static String[] tens = new String[]{"ten","eleven","twelve","thirteen","forteen","fifteen","sixteen","seventeen","eighteen","nineteen"};
	public static String[] twieties = new String[]{"zero","ten","twenty","thirty","forty","fifty","sixty","seventy","eighty","ninety"};

	public static int[] range = new int[]{(int)1e2, (int)1e3, (int)1e6, (int)1e9, (int)1e12};
	public static String[] ranges = new String[]{"hundred", "thousand", "million", "billion"};

	static class Pos {
		public int x;
		public int y;

		public Pos(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}


	public static HashMap<String,String> map = new HashMap<>(6);

	static {
		map.put("reset", "reset what");
		map.put("reset board", "board fault");
		map.put("board add", "where to add");
		map.put("board delete", "no board at all");
		map.put("reboot backplane", "impossible");
		map.put("backplane abort", "install first");
	}

	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		HashMap<String, Integer> map = new HashMap<>();
		while (sc.hasNext()) {
			String ipAndMask = sc.nextLine();
			String[] split = ipAndMask.split("~");
			String ip = split[0];
			String mask = split[1];
			int[] ints = Arrays.stream(ip.split("\\.")).mapToInt(Integer::valueOf).toArray();
			if ((0 < ints[0] && ints[0] < 10) || (10 < ints[0] && ints[0] < 127)){
				map.put("A",map.getOrDefault("A",0) + 1);
			}else if (127 < ints[0] && ints[0] < 192) {
				map.put("B",map.getOrDefault("B",0) + 1);
			}else if (192 < ints[0] && ints[0] <= 223) {
				map.put("C",map.getOrDefault("C",0) + 1);
			}else if (224 <= ints[0] && ints[0] <= 239) {
				map.put("D",map.getOrDefault("D",0) + 1);
			}else if (240 <= ints[0] && ints[0] <= 255) {
				map.put("E",map.getOrDefault("E",0) + 1);
			}else if (0 == ints[0] || ints[0] == 127) {
				map.put("不合法",map.getOrDefault("不合法",0) + 1);
			}else if (10 == ints[0] || ints[0] == 172 || ints[0] == 192 ) {
				map.put("私有",map.getOrDefault("私有",0) + 1);
			}

		}
	}

	/**
	 * 24点
	 */
	private static void extracted26() {
		Scanner sc = new Scanner(System.in);
		while (sc.hasNext()) {
			int[] num = new int[4];
			int[] visit = new int[4];
			boolean flag = false;
			for (int i = 0; i < 4; i++) {
				num[i] = sc.nextInt();
			}
			for (int i = 0; i < num.length; i++) {
				visit[i] = 1;
				if (cal24(num,visit,num[i])) {
					flag = true;
					break;
				}
			}

			System.out.println(flag);
		}
	}

	private static boolean cal24(int[] num, int[] visit, int temp) {
		for (int i = 0; i < num.length; i++) {
			if (visit[i] == 0) {
				visit[i] = 1;
				if (cal24(num,visit,temp + num[i]) || cal24(num,visit,temp - num[i]) ||
						cal24(num,visit,temp * num[i]) || (temp % num[i] == 0 && cal24(num,visit,temp / num[i]))) {
					return true;
				}
				visit[i] = 0;
			}
		}
		if (temp == 24) {
			return true;
		}
		return false;
	}
	/**
	 * HJ66,配置文件恢复
	 * 命   令				执   行
	 * reset				reset what
	 * reset board			board fault
	 * board add			where to add
	 * board delete			no board at all
	 * reboot backplane		impossible
	 * backplane abort		install first
	 * he he				unknown command
	 */
	private static void extracted25() {
		Scanner sc = new Scanner(System.in);
		while (sc.hasNext()) {
			String command = sc.nextLine();
			String[] split = command.split(" ");
			boolean flag = false;
			int count = 0;
			String resultKey = "";
			for (String key : map.keySet()) {
				String[] s = key.split(" ");
				if (split.length == 1 && s.length == 1) {
					if (key.startsWith(split[0])) {
						flag = false;
						if (count > 0) {//匹配了两条命令
							flag = true;
							count++;
						}else {
							count++;
							resultKey = key;
						}
					}
				} else if (split.length == 2 && s.length == 2) {
					if (s[0].startsWith(split[0]) && s[1].startsWith(split[1])) {
						flag = false;
						if (count > 0) {//匹配了两条命令
							count++;
							flag = true;
							break;
						}else {
							count++;
							resultKey = key;
						}
					}
				}else {
					flag = true;
				}
			}
			if (flag && count != 1) {
				System.out.println("unknown command");
			}else if (count == 1){
				System.out.println(map.get(resultKey));
			}
		}
	}

	/**
	 * 给定一个数，通过/2,+-1，最快得到1的步骤数
	 */
	private static void getScanner() {
		Scanner sc = new Scanner(System.in);
		long num = sc.nextLong();

		System.out.println(sulotion(num));
		sc.close();
	}

	public static int sulotion(long l) {
		if (l == 1) {
			return 1;
		}
		if (l == 3) {
			return 2;
		}
		int count = 0;
		while (l != 1) {
			if (l % 2 == 0) {
				count++;
				l = l /2;
			}else {
				if(l%4==1){
					l--;
					count++;
				}else {
					l++;
					count++;
				}
			}
		}
		return count;
	}

	private static String comStr(String s1, String s2) {
		if (s2.contains(s1)){
			return s1;
		}

		int len = s1.length()-1;
		for (int i = 0; i < s1.length(); i++) {
			if (len == 0 ) {
				break;
			}
			if (i + len <= s1.length()) {
				String substring = s1.substring(i, i + len);
				if (s2.contains(substring)) {
					return substring;
				}
			}else {
				len--;
				i = -1;
			}
		}
		return "";
	}

	/**
	 * HJ64，MP3光标位置
	 */
	private static void extracted24() {
		Scanner sc = new Scanner(System.in);
		while (sc.hasNext()) {
			int len = Integer.parseInt(sc.nextLine());
			String s = sc.nextLine();
			int[] arr = new int[len];
			int[] result = new int[4];
			for (int i = 0; i < len; i++) {
				arr[i] = i + 1;
			}

			for (int i = 0; i < 4; i++) {
				result[i] = i + 1;
			}
			//光标初始指向第一条歌曲
			int num = 1;

			char[] chars = s.toCharArray();
			for (int i = 0; i < chars.length; i++) {
				if (chars[i] == 'U') {
					//翻页
					if (num == 1) {
						num = len;
						for (int j = 0; j < result.length; j++) {
							result[j] = j + len - 4 + 1;
						}
					}else {
						if (num == result[0]) {
							for (int j = 0; j < result.length; j++) {
								result[j] -= 1;
							}
							num--;
						}else {
							num--;
						}
					}
				}
				if (chars[i] == 'D') {
					//翻到第一页
					if (num == len) {
						num = 1;
						for (int j = 0; j < result.length; j++) {
							result[j] = j + 1;
						}
					}else {
						if (num == result[result.length - 1]) {
							for (int j = 0; j < result.length; j++) {
								result[j] += 1;
							}
						}
						num++;
					}
				}
			}
			for (int i = 0; i < result.length; i++) {
				if (result[i] > 0) {
					System.out.print(result[i] + " ");
				}
			}
			System.out.println();
			System.out.println(num);
		}
	}

	/**
	 * HJ63，DNA序列
	 */
	private static void extracted23() {
		Scanner sc = new Scanner(System.in);
		while (sc.hasNext()) {
			String s = sc.nextLine();
			int len = Integer.parseInt(sc.nextLine());
			double num = 0.0;
			LinkedHashMap<String, Double> treeMap = new LinkedHashMap<>();
			for (int i = 0; i <= s.length() - len; i++) {
				String substring = s.substring(i, i + len);
				int count = 0;
				for (int j = 0; j < substring.length(); j++) {
					if (substring.charAt(j) == 'G' || substring.charAt(j) == 'C') {
						count++;
					}
				}
				num = count * 1.0 / substring.length();
				treeMap.put(substring,num);
			}
			double temp = 0;
			for (Map.Entry entry : treeMap.entrySet()) {
				temp = Math.max((Double) entry.getValue(), temp);
			}
			boolean flag = true;
			for (String str : treeMap.keySet()){
				if (treeMap.get(str) == temp && flag) {
					System.out.println(str);
					flag = false;
				}
			}
		}
	}

	/**
	 * 盘子装水果
	 */
	private static void extracted22() {
		Scanner sc = new Scanner(System.in);
		while(sc.hasNext()) {
			int apples = sc.nextInt();//苹果
			int panels = sc.nextInt();//盘子

			int[][] dp = new int[apples+1][panels+1];
			for (int i = 0; i <= panels; i++) {
				dp[1][i] = 1;//只有一个苹果，不管多少个盘子都只有一种
				dp[0][i] = 1;
			}
			for (int i = 0; i <= apples ; i++) {
				dp[i][1] = 1;//只有一个盘子
			}

			for (int i = 2; i <= apples; i++) {
				for (int j = 1; j <= panels ; j++) {
					//i个苹果放到j个盘子里的方法数，等于所有盘子都有苹果的方法 + 部分盘子没有苹果的方法
					//所有盘子都有苹果，等于每个盘子去掉一个苹果，
					//部分盘子没有苹果，先假设一个盘子没有苹果，剩下的盘子到底怎么分配苹果，慢慢算
					dp[i][j] = dp[i][j - 1] + (i - j >= 0 ? dp[i - j][j] : 0);
				}
			}
			System.out.println(dp[apples][panels]);
		}
	}

	/**
	 * HJ60, 查找组成一个偶数最接近的两个素数
	 */
	private static void extracted21() {
		Scanner sc = new Scanner(System.in);
		while(sc.hasNext()){
			int n = sc.nextInt();
			for(int i=n/2;i>=2;i--){
				if(isPrime(i)&&isPrime(n-i)){
					System.out.println(i);
					System.out.println(n-i);
					break;
				}
			}
		}
	}

	public static boolean isPrime(int n){
		for(int i=2;i<n;i++){
			if(n%i==0){
				return false;
			}
		}
		return true;
	}

	/**
	 * HJ59,找出字符串第一个出现的字母
	 */
	private static void extracted20() {
		Scanner sc = new Scanner(System.in);
		while (sc.hasNext()) {
			String s = sc.nextLine();
			if (s.length() == 1){
				System.out.println(s);
			}else {
				char[] chars = s.toCharArray();
				boolean flag = true;
				for (int i = 0; i < chars.length; i++) {
					if (!s.substring(i+1,s.length()).contains(String.valueOf(chars[i])) && s.substring(0,i)!= "" && !s.substring(0,i).contains(String.valueOf(chars[i]))){
						System.out.println(chars[i]);
						flag = false;
						break;
					}
				}
				if (flag) {
					System.out.println(-1);
				}
			}

		}
	}

	/**
	 * HJ58,找出其中最小的k个数
	 */
	private static void extracted19() {
		Scanner sc = new Scanner(System.in);
		while (sc.hasNext()) {
			int n = sc.nextInt();
			int k = sc.nextInt();
			ArrayList<Integer> list = new ArrayList<>();
			for (int i = 0; i < n; i++) {
				int num = sc.nextInt();
				list.add(num);
			}
			list.sort(Comparator.comparingInt(i -> i));
			for (int i = 0; i < k; i++) {
				System.out.print(list.get(i) + " ");
			}
		}
	}

	/**
	 * HJ57，高精度加减
	 */
	private static void extracted18() {
		Scanner sc = new Scanner(System.in);
		while (sc.hasNext()) {
			String str1 = sc.nextLine();
			String str2 = sc.nextLine();
			int i = str1.length()-1;
			int j = str2.length()-1;
			Stack<Integer> stack = new Stack<>();
			boolean needCarry = false;
			while (i >=0 || j >=0){
				char c1 = '0';
				char c2 = '0';
				int result = 0;
				if (i>=0){
					c1 = str1.charAt(i);
				}
				if (j>=0){
					c2 = str2.charAt(j);
				}
				result = (c1 - 48) + (c2 - 48) + (needCarry ? 1 : 0);
				needCarry = false;
				if (result > 9){
					needCarry = true;
					result%=10;
				}
				stack.push(result);
				i--;
				j--;
			}
			if (needCarry) {
				// 如果需要进位，表示最后计算的值大于10,所以最高位为1
				stack.push(1);
			}
			while (!stack.isEmpty()){
				System.out.print(stack.pop());
			}

		}
	}

	/**
	 * HJ56,完全数
	 */
	private static void extracted17() {
		Scanner sc = new Scanner(System.in);
		while (sc.hasNext()) {
			int num = sc.nextInt();
			int count = 0;
			for (int i = 1; i < num; i++) {
				int sum = 0;
				//查找该数的所有因子
				for (int j = 1; j < i; j++) {
					if (i%j==0){
						sum += j;
					}
				}
				if (i == sum){
					count++;
				}
			}
			System.out.println(count);
		}
	}

	/**
	 * HJ55,挑7
	 */
	private static void extracted16() {
		Scanner sc = new Scanner(System.in);
		while (sc.hasNext()) {
			int num = sc.nextInt();
			LinkedList<Integer> list = new LinkedList<>();
			for (int i = 1; i <= num; i++) {
				if (i % 7 == 0){
					list.add(i);
				}else if (String.valueOf(i).indexOf("7") != -1) {
					list.add(i);
				}
			}
			System.out.println(list.size());
		}
	}

	/**
	 * HJ52,编辑距离
	 */
	private static void extracted15() {
		Scanner sc = new Scanner(System.in);
		while (sc.hasNext()){
			String str1 = sc.nextLine();
			String str2 = sc.nextLine();
			int[][] dp = new int[str1.length()+1][str2.length()+1];
			dp[0][0] = 0;
			for (int i = 1; i < dp.length; i++) {
				dp[i][0] = i;
			}
			for (int i = 1; i < dp[0].length; i++) {
				dp[0][i] = i;
			}
			for (int i = 1; i < dp.length; i++) {
				for (int j = 1; j < dp[i].length; j++) {
					if (str1.charAt(i-1) == str2.charAt(j-1)) {
						dp[i][j] = dp[i-1][j-1];
					}else {
						dp[i][j] = Math.min(dp[i-1][j-1]+1,Math.min(dp[i][j-1]+1,dp[i-1][j]+1));
					}
				}
			}
			System.out.println(dp[str1.length()][str2.length()]);
		}
	}

	/**
	 * 输出单向链表倒数第k个节点，HJ51
	 */
	private static void extracted14() {
		Scanner sc = new Scanner(System.in);
		while (sc.hasNext()){
			int count = sc.nextInt();
			LinkedList<Integer> list = new LinkedList<>();
			for (int i = 0; i < count; i++) {
				int num = sc.nextInt();
				list.add(num);
			}
			int index = sc.nextInt();

			System.out.println(list.get(list.size()-index));
		}
	}

	/**
	 * HJ50,四则运算
	 */
	private static void extracted13() {
		Scanner sc=new Scanner(System.in);
		String s=sc.nextLine();
		//将其他括号，替换成小括号
		s=s.replace("{","(");
		s=s.replace("[","(");
		s=s.replace("}",")");
		s=s.replace("]",")");
		System.out.println(slove(s));
	}


	private static int slove(String s) {
		Stack<Integer> stack = new Stack<>();
		int length = s.length();
		char[] chars = s.toCharArray();
		int index = 0;

		//初始化符号为+
		char sign = '+';
		//记录数字
		int number = 0;

		for (int i = 0; i < chars.length; i++) {
			char c = chars[i];
			if (c == ' '){
				continue;
			}else if (Character.isDigit(c)){//如果是数字，则拼接数组
				number += number*10+c-'0';
			}else if (c == '('){
				//括号个数
				int count = 1;
				//移到括号后以为重新计算
				int j = i+1;
				while (count>0){
					if (chars[j] == ')') count--;
					if (chars[j] == '(') count++;
					j++;
				}
				number = slove(s.substring(i+1,j-1));
				i = j-1;
			}
			//遇到数字，处理后在放入栈中
			if (!Character.isDigit(c) || i == length-1){
				//如果是'+',直接入栈
				if(sign=='+'){
					stack.push(number);
				}
				//如果是'-',数字取相反数在入栈
				else if(sign=='-'){
					stack.push(-1*number);
				}
				//如果是'*',弹出一个数字乘后放入栈
				else if(sign=='*'){
					stack.push(stack.pop()*number);
				}
				//如果是'/',弹出一个数字/后放入栈
				else if(sign=='/'){
					stack.push(stack.pop()/number);
				}
				//更新符号
				sign=c;
				//刷新数字
				number=0;
			}
		}
		//栈中数字求和得到结果
		int ans=0;
		while(!stack.isEmpty()){
			ans+=stack.pop();
		}
		return ans;
	}


	private static void extracted13(String s) {

	}
	/**
	 * HJ48,从链表删除指定值得节点
	 */
	private static void extracted12() {
		Scanner sc = new Scanner(System.in);
		while (sc.hasNext()) {
			int total = sc.nextInt();
			int head = sc.nextInt();

			List<Integer> linkedlist = new ArrayList<>();
			linkedlist.add(head);
			for (int i = 0; i < total - 1; i ++) {
				int value = sc.nextInt();
				int target = sc.nextInt();
				linkedlist.add(linkedlist.indexOf(target) + 1, value);
			}

			int remove = sc.nextInt();
			linkedlist.remove(linkedlist.indexOf(remove));
			for (int i : linkedlist) {
				System.out.print(i + " ");
			}
			System.out.println();
		}
	}

	/**
	 * 截取字符串HJ46
	 */
	private static void extracted11() {
		Scanner sc = new Scanner(System.in);
		while (sc.hasNext()) {
			String s = sc.nextLine();
			int i = sc.nextInt();
			System.out.println(s.substring(0,i));
		}
	}

	/**
	 * HJ45，字符串漂亮度，贪心算法
	 */
	private static void extracted10() {
		Scanner sc = new Scanner(System.in);
		while (sc.hasNext()) {
			int num = sc.nextInt();
			for (int i = 0; i < num; i++) {
				String next = sc.next().toLowerCase();
				//每个字符出现的次数
				HashMap<Character, Integer> map = new HashMap<>();
				for (Character c : next.toCharArray()){
					map.put(c,map.getOrDefault(c,0)+1);
				}
				ArrayList<Integer> list = new ArrayList<>(map.values());
				list.sort((l1,l2)->l2-l1);
				int mul = 26;
				int result = 0;
				for (Integer e : list) {
					result += e * mul;
					mul--;
				}
				System.out.println(result);
			}
		}
	}

	/**
	 * 九宫格填充，HJ44
	 */
	private static void extracted9() {
		Scanner sc = new Scanner(System.in);
		while (sc.hasNext()) {
			int n = sc.nextInt();
			int m = sc.nextInt();
			int[][] array = new int[n][m];
			for (int x = 0; x < n; x++) {
				for (int y = 0; y < m; y++) {
					array[x][y] = sc.nextInt();
				}
			}
			dfsShuDu(array,0,0);
		}
	}

	private static boolean dfsShuDu(int[][] array, int x, int y) {
		//(9,0)坐标
		if (x==9){
			return true;
		}
		if (array[x][y] == 0) {
			for (int i = 1; i < 9; i++) {
				array[x][y] = i;
				if (checked(array,x,y) && dfsShuDu(array, y == 8 ? x + 1 : x, y == 8 ? 0 : y + 1)){
					return true;
				}
			}
			array[x][y] = 0;// 回溯
			return false;
		}else {
			return dfsShuDu(array, y == 8 ? x + 1 : x, y == 8 ? 0 : y + 1);
		}
	}

	private static boolean checked(int[][] array, int x, int y) {
		//同行
		for (int k = 0;k<9;k++){
			if (k!=y && array[x][k]==array[x][y]){
				return false;
			}
		}
		for (int k = 0; k < 9; k++) {
			if (k != x && array[k][y] == array[x][y]) {
				return false;
			}
		}

		// 同九宫
		int up = x / 3 * 3;
		int left = y / 3 * 3;
		for (int k = up; k < up + 3; k++) {
			for (int l = left; l < left + 3; l++) {
				if ((k != x || l != y) && array[k][l] == array[x][y]) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * HJ43,迷宫问题，深度搜索
	 */
	private static void extracted8() {
		Scanner sc = new Scanner(System.in);
		while (sc.hasNext()) {
			int n = sc.nextInt();
			int m = sc.nextInt();
			int[][] array = new int[n][m];
			for (int x = 0; x < n; x++) {
				for (int y = 0; y < m; y++) {
					array[x][y] = sc.nextInt();
				}
			}
			//存储路径的list
			ArrayList<Pos> pos = new ArrayList<>();
			//dfs搜索路径
			dfs(array,0,0,pos);
			for (Pos po : pos) {
				System.out.println("("+po.x+","+po.y+")");
			}
		}
	}

	private static boolean dfs(int[][] array, int x, int y, ArrayList<Pos> pos) {
		//添加已有路径并标记已走
		pos.add(new Pos(x,y));
		array[x][y] = 1;
		//结束标志
		if (x == array.length-1 && y == array[0].length-1){
			return true;
		}
		//向下能走
		if (x + 1 < array.length && array[x+1][y] == 0){
			if (dfs(array,x+1,y,pos)){
				return true;
			}
		}

		//向右能走
		if (y + 1 < array[x].length && array[x][y+1] == 0){
			if (dfs(array,x,y+1,pos)){
				return true;
			}
		}
		//向上能走
		if (x -1 > -1 && array[x-1][y] == 0){
			if (dfs(array,x-1,y,pos)){
				return true;
			}
		}
		//向左能走
		if (y -1 > -1 && array[x][y-1] == 0){
			if (dfs(array,x,y-1,pos)){
				return true;
			}
		}
		//回溯
		pos.remove(pos.size()-1);
		array[x][y] = 0;
		return false;
	}

	/**
	 * HJ42，学英语，数字转单词
	 */
	private static void extracted7() {
		Scanner sc = new Scanner(System.in);
		while (sc.hasNext()) {
			long l = sc.nextLong();
			System.out.println(transfer((int) l));
		}
	}

	static String transfer(int num){
		// terminor
		if(num <= 9) return ones[num];
		if(num <= 19) return tens[num % 10];
		if(num <= 99) return twieties[num / 10] + (num % 10 == 0 ? "" : " " + ones[num % 10]);
		// 递归调用
		for(int i=0; i<4; i++){
			if(num < range[i + 1]){
				return transfer(num / range[i]) + " " + ranges[i] +
						(num % range[i] == 0 ? " " : (i != 0 ? " " : " and ") + transfer(num % range[i]));
			}
		}
		return "";
	}

	/**
	 * HJ41 数砝码
	 */
	private static void extracted6() {
		Scanner sc = new Scanner(System.in);
		while (sc.hasNext()) {
			int num = sc.nextInt();
			int[] qualityArray = new int[num];
			int[] countArray = new int[num];
			for (int i = 0; i < num; i++) {
				qualityArray[i] = sc.nextInt();
			}
			for (int i = 0; i < num; i++) {
				countArray[i] = sc.nextInt();
			}
			LinkedHashSet<Integer> set = new LinkedHashSet<>();
			set.add(0);
			for(int i=0;i<num;i++){//遍历种类
				ArrayList<Integer> list = new ArrayList<>(set);//取当前所有的结果
				for(int j=1;j<=countArray[i];j++){//遍历个数
					for(int k=0;k<list.size();k++){
						set.add(list.get(k) + qualityArray[i] * j);
					}
				}
			}
			System.out.println(set.size());
		}
	}

	/**
	 * HJ40,统计字母空格，数字，其他字符出现次数
	 */
	private static void extracted5() {
		Scanner sc = new Scanner(System.in);
		while (sc.hasNext()){
			String s = sc.nextLine();
			String s1 = s.replaceAll("[A-Z]+|[a-z]+", "");
			System.out.println(s1);
			System.out.println(s.length() - s1.length());
		}
	}

	/**
	 * HJ39，判读两个ip是否在同一子网下，
	 */
	private static void extracted4() {
		Scanner sc = new Scanner(System.in);
		while (sc.hasNext()){
			String s = sc.nextLine();
			String ip1 = sc.nextLine();
			String ip2 = sc.nextLine();

			//判断是否合法
			if (!isLegal(s) || !isLegal(ip1) || !isLegal(ip2)){
				System.out.println(1);
				continue;
			}
			String[] split = s.split("\\.");
			int[] s1 = Arrays.stream(s.split("\\.")).mapToInt(Integer::valueOf).toArray();
			int[] ip11 = Arrays.stream(ip1.split("\\.")).mapToInt(Integer::valueOf).toArray();
			int[] ip21 = Arrays.stream(ip2.split("\\.")).mapToInt(Integer::valueOf).toArray();

			//掩码转换二进制
			StringBuilder builder = new StringBuilder();
			for (int i = 0; i < s1.length; i++) {
				builder.append(Integer.toBinaryString(s1[2]));
			}

			boolean flag = true;
			for (int i = 0; i < 4; i++) {
				if ((s1[i]&ip11[i]) != (s1[i]&ip21[i])){
					System.out.println(2);
					flag = false;
				}
			}
			if (flag) {
				System.out.println(0);
			}
		}
	}

	private static boolean isLegal(String s) {
		String[] split = s.split("\\.");
		if (split.length!=4)
			return false;
		for (String s1 : split) {
			if (Integer.parseInt(s1) < 0 || Integer.parseInt(s1) > 255) {
				return false;
			}
		}
		return true;
	}


	/**
	 * 弹球高度HJ38
	 */
	private static void extracted3() {
		final int COUNT = 5;
		Scanner sc = new Scanner(System.in);
		while (sc.hasNext()){
			int heigh = sc.nextInt();
			double sum = 0;
			double resultHeigh = heigh;
			for (int i = 0; i < COUNT; i++) {
				sum += resultHeigh*2;
				resultHeigh = resultHeigh/2;

			}
			sum-= heigh;
			System.out.printf("%.6f\n",sum);
			System.out.printf("%.6f\n",resultHeigh);
		}
	}

	/**
	 * 数兔子，HJ37,斐波那契数列
	 */
	private static void extracted2() {
		Scanner sc = new Scanner(System.in);
		while (sc.hasNext()){
			int month = sc.nextInt();

			System.out.println(fii(month));
		}
	}

	private static int fii(int month) {
		if (month < 0) {
			return -1;
		}
		int count = 0;
		if (month <= 2){
			count = 1;
		}else {

			count = fii(month-1) + fii(month-2);
		}
		return count;
	}

	/**
	 * 字符串解密HJ36
	 */
	private static void extracted1() {
		Scanner sc = new Scanner(System.in);
		while (sc.hasNext()){
			//密钥组成部分
			String s = sc.nextLine();
			//通过密钥要获取的密码
			String str = sc.nextLine();

			//获取所有密钥 1.去重，添加剩余字母
			char[] chars1 = s.toLowerCase().toCharArray();
			LinkedHashSet<Character> set = new LinkedHashSet<>();
			for (int i = 0; i < chars1.length; i++) {
				set.add(chars1[i]);
			}
			int temp = 0;
			while (set.size() < 26){
				set.add((char)('a' + temp));
				temp++;
			}
			ArrayList<Character> arrayList = new ArrayList<>(set);
			String reg = "abcdefghijklmnopqrstuvwxyz";
			char[] chars = str.toLowerCase().toCharArray();
			StringBuilder builder = new StringBuilder();
			for (int i = 0; i < chars.length; i++) {
				int i1 = reg.indexOf(chars[i]);
				builder.append(arrayList.get(i1));
			}
			System.out.println(builder);
		}
	}

	/**
	 * 蛇形矩阵HJ35
	 */
	private static void matrix() {
		Scanner sc = new Scanner(System.in);
		while (sc.hasNext()){
			int num = sc.nextInt();
			int[][] arr = new int[num][];
			int temp = 1;

			for (int i = 0; i < num; i++) {
				//计算每行具备的列数
				arr[i] = new int[num-i];
				for (int j = 0; j < i+1	; j++) {
					arr[i-j][j] = temp;
					temp ++;
				}
			}
			//输出数组
			for (int i = 0; i < num; i++) {
				for (int j = 0; j < arr[i].length; j++) {
					System.out.print(arr[i][j] + " ");
				}
				System.out.println();
			}
		}
	}

	/**
	 * 图片整理------字符串排序
	 */
	private static void strSort() {
		Scanner scanner = new Scanner(System.in);
		while (scanner.hasNext()){
			String s = scanner.nextLine();
			char[] a = s.toCharArray();
			Arrays.sort(a);
			System.out.println(new String(a));
		}
	}

	/**
	 * ip和整数相互转换
	 */
	private static void ipToNum() {
		Scanner sc = new Scanner(System.in);
		while (sc.hasNext()) {
			String[] ips = sc.nextLine().split("\\.");
			long ocIp = sc.nextLong();
			StringBuilder sb = new StringBuilder();
			for (String ip : ips) {
				String binary = Integer.toBinaryString(Integer.parseInt(ip));
				//判断二级制长度是否为8，小于8添加0
				while (binary.length() < 8){
					binary = "0" + binary;
				}
				sb.append(binary);
			}
			System.out.println(Integer.parseInt(sb.toString(),2));
			String binaryString = Long.toBinaryString(ocIp);
			while (binaryString.length() < 32){
				binaryString = "0" + binaryString;
			}
			String[] array = new String[4];
			StringBuilder sb1 = new StringBuilder();
			for (int i = 0; i < 4; i++) {
				String substring = binaryString.substring(i*8, i*8+8);
				int i1 = Integer.parseInt(substring, 2);
				sb1.append(i1).append(".");
			}
			StringBuilder builder = sb1.deleteCharAt(sb1.length() - 1);

			System.out.println(builder);
		}
	}

	/**
	 * 找出最长回文字符串
	 */
	private static void huiwenShu() {
		//中心扩散回文数最长
		Scanner sc = new Scanner(System.in);
		String s = sc.nextLine();
		System.out.println(solution(s));
	}

	private static int solution(String s) {
		int res = 0;
		for(int i = 0; i < s.length(); i++) {
			// ABA型
			int len1 = longest(s, i, i);
			// ABBA型
			int len2 = longest(s, i, i + 1);
			res = Math.max(res, len1 > len2 ? len1 : len2);
		}
		return res;
	}

	private static int longest(String s, int l, int r) {
		while(l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)) {
			l--;
			r++;
		}
		return r - l - 1;
	}
	private static void extracted() {
		Scanner sc = new Scanner(System.in);
		while (sc.hasNext()) {
			String s = sc.nextLine();
			char[] chars = s.toCharArray();
			for (int i = 0; i < chars.length; i++) {
				if (!Character.isLetter(chars[i])){
					chars[i] = ' ';
				}
			}
			String str = new String(chars);
			String[] strings = str.split(" ");
			for (int i = strings.length-1; i >= 0; i--) {
				System.out.print(strings[i] + " ");
			}
		}
	}

	/**
	 * 字符串合并处理
	 * 第一步：将输入的两个字符串str1和str2进行前后合并。如给定字符串 "dec" 和字符串 "fab" ， 合并后生成的字符串为 "decfab"
	 *
	 * 第二步：对合并后的字符串进行排序，要求为：下标为奇数的字符和下标为偶数的字符分别从小到大排序。这里的下标的意思是字符在字符串中的位置。注意排序后在新串中仍需要保持原来的奇偶性。例如刚刚得到的字符串“decfab”，分别对下标为偶数的字符'd'、'c'、'a'和下标为奇数的字符'e'、'f'、'b'进行排序（生成 'a'、'c'、'd' 和 'b' 、'e' 、'f'），再依次分别放回原串中的偶数位和奇数位，新字符串变为“abcedf”
	 *
	 * 第三步：对排序后的字符串中的'0'~'9'、'A'~'F'和'a'~'f'字符，需要进行转换操作。
	 * 转换规则如下：
	 * 对以上需要进行转换的字符所代表的十六进制用二进制表示并倒序，然后再转换成对应的十六进制大写字符（注：字符 a~f 的十六进制对应十进制的10~15，大写同理）。
	 * 如字符 '4'，其二进制为 0100 ，则翻转后为 0010 ，也就是 2 。转换后的字符为 '2'。
	 * 如字符 ‘7’，其二进制为 0111 ，则翻转后为 1110 ，对应的十进制是14，转换为十六进制的大写字母为 'E'。
	 * 如字符 'C'，代表的十进制是 12 ，其二进制为 1100 ，则翻转后为 0011，也就是3。转换后的字符是 '3'。
	 * 根据这个转换规则，由第二步生成的字符串 “abcedf” 转换后会生成字符串 "5D37BF"。
	 */
	private static void string() {
		Scanner sc = new Scanner(System.in);
		while (sc.hasNext()){
			String firstStr = sc.next();
			String secondStr = sc.next();
			String tempStr = firstStr + secondStr;

			char[] chars = tempStr.toCharArray();
			//奇数字符串
			StringBuilder sb = new StringBuilder();
			//偶数字符串
			StringBuilder stringBuilder = new StringBuilder();
			for (int i = 0; i < chars.length; i++) {
				if(i%2==0){
					sb.append(chars[i]);
				}else {
					stringBuilder.append(chars[i]);
				}
			}
			//排序
			char[] a = sb.toString().toCharArray();
			Arrays.sort(a);
			char[] b = stringBuilder.toString().toCharArray();
			Arrays.sort(b);
			StringBuilder tempSb = new StringBuilder();
			//按原来顺序拼接
			int oddIndex = 0;
			int evenIndex  = 0;
			for (int i = 0; i < tempStr.length(); i++) {
				if (i%2==0){
					tempSb.append(a[evenIndex]);
					evenIndex++;
				}else {
					tempSb.append(b[oddIndex]);
					oddIndex++;
				}
			}
			//进制转换
			String rule = "abcdefABCDEF0123456789";
			char[] sortedChar = tempSb.toString().toCharArray();

			for(int m = 0; m < sortedChar.length; m++){
				//如果是0-9,a-f,A-F
				if(rule.contains(String.valueOf(sortedChar[m]))){
					//转换为十进制
					int decimal = Integer.parseInt(String.valueOf(sortedChar[m]),16);
					//转换为二进制,加leading zeros
					String binary = Integer.toString(decimal,2);
					binary = String.format("%04d", Integer.valueOf(binary));
					//翻转
					String reverseBinary = new StringBuilder(binary).reverse().toString();
					//转换回16进制，如果是小写字符，变大写
					//实际上这里无需判断是否是别的字符，因为四位二进制最大也就是1111，对应f,所以直接转换成大写
					decimal = Integer.parseInt(reverseBinary,2);
					String hex = Integer.toString(decimal,16).toUpperCase();
					sortedChar[m] = hex.charAt(0);
				}
			}
			System.out.println(sortedChar);
		}
	}


	/**
	 * 字符串加解密
	 * 对输入的字符串进行加解密，并输出。
	 *
	 * 加密方法为：
	 *
	 * 当内容是英文字母时则用该英文字母的后一个字母替换，同时字母变换大小写,如字母a时则替换为B；字母Z时则替换为a；
	 *
	 * 当内容是数字时则把该数字加1，如0替换1，1替换2，9替换0；
	 *
	 * 其他字符不做变化。
	 */
	private static void encryptString(){
		Scanner sc = new Scanner(System.in);
		while (sc.hasNext()) {
			//解密字符串
			String s = sc.nextLine();
			//加密字符串
			String str = sc.nextLine();
			StringBuilder sb = new StringBuilder();
			for(char c : s.toCharArray()){
				if ('a' <= c && c <= 'y'){
					sb.append(Character.toUpperCase((char) (c + 1)));
				}else if(c == 'z'){
					sb.append('A');
				}else if(c == 'Z'){
					sb.append('a');
				}else if('A' <= c && c <= 'Y'){
					sb.append(Character.toLowerCase((char)(c+1)));
				}else if ('0' <= c && c< '9'){
					sb.append((char)(c+1));
				}else if (c== '9'){
					sb.append('0');
				}
			}
			StringBuilder stringBuilder = new StringBuilder();
			for(char c : str.toCharArray()){
				if ('b' <= c && c <= 'z'){
					stringBuilder.append(Character.toUpperCase((char) (c - 1)));
				}else if(c == 'a'){
					stringBuilder.append('Z');
				}else if(c == 'A'){
					stringBuilder.append('z');
				}else if('B' <= c && c <= 'Z'){
					stringBuilder.append(Character.toLowerCase((char)(c-1)));
				}else if ('1' <= c && c<= '9'){
					stringBuilder.append((char)(c-1));
				}else if (c== '0'){
					stringBuilder.append('9');
				}
			}
			System.out.println(sb);
			System.out.println(stringBuilder);
		}
	}
}
