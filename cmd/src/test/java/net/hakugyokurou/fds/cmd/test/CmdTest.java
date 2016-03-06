package net.hakugyokurou.fds.cmd.test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import net.hakugyokurou.fds.cmd.FDSCmd;

public class CmdTest {

	private static boolean debug = true;
	
	private static PrintStream defaultOutput;
	private static PrintStream dummyOutput;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		if(debug)
			return;
		defaultOutput = System.out;
		dummyOutput = new PrintStream(new DummyOutputStream());
		System.setOut(dummyOutput);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		if(debug)
			return;
		System.setOut(defaultOutput);
		try {
			dummyOutput.close();
		} catch (Exception e) {}
	}

	@Test
	public void basicTest() {
		assertEquals(FDSCmd.RESULT_SUCCESS, FDSCmd.command(new String[] {"-h"}));
	}
	
	private static class DummyOutputStream extends OutputStream {

		@Override
		public void write(int b) throws IOException {
			/*
						 　　　　　　　 r-----、 _ -　, -――┐
			　　　　 　 　 { |`ヾ＜　　　　 ／´￣￣ } | ､
			　　　　 　 　 { | ／＿_ヽｧ=彡' 　 　 　 //　 ＼
			　 　 　 　 ___{ └--{⌒ヽ　　¨∠⌒ ー=--ﾐj {　　　　_        Just do nothing. Ha.
			 　　　 　  ／￣    \ /ヽノ ｛ /＼ノ　|＿__ヾー ､´　 ヽ
			　　　　　 ／____  ::У　 ＼_j′/!i＼ ＼} |　〕　　＼　|
			　　　　  〔￣   厂 i:l　ヽ|　 j´ / 川｀ ￣　!ノ　　　　∨
			　 　 　 　 7　  |　j ! ,_i_| /'| /_____j,/　|　{＿＿＿　∧
			 　　 　 　/ ／ :! 从i 花|　|´花イ′/　.八　　　{｀ヽjヽ
			 　　　 　 ー―---| |}i ノ　 　 ,.,.,.,.|　/　./　 ヽ　　| ／　 ＼
			 　　　　　　 　 i八.ハヽ　　　　　川 ／　 j　　　└z‐ ミ　 }
			　  　　　 　 　  └‐l　 　 ｰ　　　　　|'　 　 ′ i　　　∨　｀V
			　　　　　 　 　   l　i ∧｀　　 ,　イ|　　　|　 :|.　　i　∨〔
			　　　　　 　 　   |　!{ ｉ:｀¨爪ヽ_／|　　 ∧ ' ￣ ヽ　 ＼
			　　　　　 　 　   |　!|:! 　l ! |　}| /  /::/　　　　 ＼　 ヽ
			　　　 　 　  　./ |　!|:! 　l ! |ノ }j  /::'　　　　　 　 ヽ　 ＼
			　　　　　 　   / ,|　!|:! 　l ! |::}|  l|:::l　　　　　 　 　 ∨⌒ヽ
			　　　　 　   ./ /:|　!|:! 　l ! |::}|　l|:::,　 　 　 　 　 　 ∨ ＼＼
			　　　　　　　/ / /  /:|:!   l ! |:／   ト`_jr‐┐　　　　　　j′　Ⅵヽ＼＼
			　　　　　　./ /{ :!{ト‐=ミ _l厶 "´}　{_j　 ハ　　　　　 {　　　}＼　　ヾ:.
			　　 　 　 ./ / :||≫°::::::⌒ハ´::::!　　　　:!　 !、 ヽ　　}:|ノ{
			　　　　 　/{´/ ∠二二}:::／::{_　 ーく{＿＿_,:{  }i |＿ヽrイ　{
			　　　 　 | | ／::::::::∠二二}:≫ -‐-----ミ-＿,|   }| ヽ　 :}_j　　}
			　　　 　 `-=/:::::::::/::::::::/l/　　 ｀ヽ　|   }l|　}　ｲ　｀ー
			　　　　　　{:::::ー ＝== -::::/l/ 　 　/:/   |   }ﾊ;レ {　　　 ＼
			　　　　　  ∧:::::::::|:::::::|/{　　 　イ'　 |   }/八　＼ 　 　 |
			　　　　   / ∧::::::::|;:::::::V:{　   :!:    |  ｛/|::≫.｡｀ヽ　/
			　　　 　./ /.ヽ:::::::\::::::::V{　 　./ 　 /_ (／ヽ:::::......
			　　　　 / /　 /` ー--　＼:::::ヾ＼　_ノ!　 }_｀／／::::::::::｀ヽ
			　　　|\j ｛ ./　   |　 i ` ー―‐ ¨´　 ヽ　 /:::く／\/〉_　- ―――
			　　　|　ヽ}厶、　　|　 |   |　l ＞.,　　 /:::: ´\／/ー―　´￣￣
			　 　 j　/｀Y /.　 .| 　|   |  j　j|｀ヽ¨´:::::::く/! 
			 */
		}

		@Override
		public void close() throws IOException {
		}
	}
}
