package com.zrsf.backup.http.inner;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class VariCodeIdentifier {	
		private static String a00 = "00000111111100000000";
		private static String a01 = "00011111111111000000";
		private static String a02 = "00111000000011100000";
		private static String a03 = "01100000000000110000";
		private static String a04 = "01100000000000110000";
		private static String a05 = "01100000000000110000";
		private static String a06 = "01100000000001110000";
		private static String a07 = "00111000000011100000";
		private static String a08 = "00011111111111000000";
		private static String a09 = "00000111111100000000";

		private static String a10 = "00110000000000110000";
		private static String a11 = "00110000000000110000";
		private static String a12 = "01110000000000110000";
		private static String a13 = "01111111111111110000";
		private static String a14 = "01111111111111110000";
		private static String a15 = "00000000000000110000";
		private static String a16 = "00000000000000110000";
		private static String a17 = "00000000000000110000";

		private static String a20 = "00110000000001110000";
		private static String a21 = "01100000000011110000";
		private static String a22 = "01100000000110110000";
		private static String a23 = "01100000001100110000";
		private static String a24 = "01100000011000110000";
		private static String a25 = "01100000110000110000";
		private static String a26 = "01110001100000110000";
		private static String a27 = "00111111000000110000";
		private static String a28 = "00011110000000110000";

		private static String a30 = "00110000000001100000";
		private static String a31 = "01100001100000110000";
		private static String a32 = "01100001100000110000";
		private static String a33 = "01100001100000110000";
		private static String a34 = "01100001100000110000";
		private static String a35 = "01110010110001110000";
		private static String a36 = "00111110111111100000";
		private static String a37 = "00011100011111000000";

		private static String a40 = "00000000001100000000";
		private static String a41 = "00000000111100000000";
		private static String a42 = "00000001111100000000";
		private static String a43 = "00000011001100000000";
		private static String a44 = "00001110001100000000";
		private static String a45 = "00011100001100000000";
		private static String a46 = "00110000001100000000";
		private static String a47 = "01111111111111110000";
		private static String a48 = "01111111111111110000";
		private static String a49 = "00000000001100000000";
		private static String a401 = "00000000001100000000";
		//
		private static String a50 = "01111111000001100000";
		private static String a51 = "01111111000000110000";
		private static String a52 = "01100011000000110000";
		private static String a53 = "01100011000000110000";
		private static String a54 = "01100011100000110000";
		private static String a55 = "01100001100001100000";
		private static String a56 = "01100001111111100000";
		private static String a57 = "01100000011111000000";

		private static String a60 = "00000111111100000000";
		private static String a61 = "00011111111111000000";
		private static String a62 = "00111001100011100000";
		private static String a63 = "01110001000001110000";
		private static String a64 = "01100011000000110000";
		private static String a65 = "01100011000000110000";
		private static String a66 = "01100011000000110000";
		private static String a67 = "01100011100001100000";
		private static String a68 = "00110001111111100000";
		private static String a69 = "00000000111110000000";

		private static String a70 = "01100000000000000000";
		private static String a71 = "01100000000000110000";
		private static String a72 = "01100000000111110000";
		private static String a73 = "01100000001111000000";
		private static String a74 = "01100000111000000000";
		private static String a75 = "01100011100000000000";
		private static String a76 = "01101110000000000000";
		private static String a77 = "01111000000000000000";
		private static String a78 = "01110000000000000000";

		private static String a80 = "00000000001111000000";
		private static String a81 = "00011100011111100000";
		private static String a82 = "00111110110001110000";
		private static String a83 = "01110011100000110000";
		private static String a84 = "01100001100000110000";
		private static String a85 = "01100001100000110000";
		private static String a86 = "01110011110000110000";
		private static String a87 = "00111110111001100000";
		private static String a88 = "00011100011111100000";
		private static String a89 = "00000000001111000000";

		private static String a90 = "00001111100000000000";
		private static String a91 = "00111111110001100000";
		private static String a92 = "00110000111000110000";
		private static String a93 = "01100000011000110000";
		private static String a94 = "01100000011000110000";
		private static String a95 = "01100000011000110000";
		private static String a96 = "01110000010001100000";
		private static String a97 = "00111000110011100000";
		private static String a98 = "00011111111111000000";
		private static String a99 = "00000111111100000000";

		public static String identify(InputStream in){
			StringBuffer result = new StringBuffer();
			int a[] = new int[10];
			a[0] = 0;
			a[1] = 0;
			a[2] = 0;
			a[3] = 0;
			a[4] = 0;
			a[5] = 0;
			a[6] = 0;
			a[7] = 0;
			a[8] = 0;
			a[9] = 0;
			boolean b = false;
			int index = 0;
			BufferedImage bi=null;
			try {
				bi = ImageIO.read(in);
			} catch (IOException e) {				
				e.printStackTrace();
			}			
			int h = bi.getHeight();
			int w = bi.getWidth();
			for (int x = 0; x < w; x++) {
				StringBuffer numstr = new StringBuffer();
				for (int y = 0; y < h; y++) {
					if (getGray(bi.getRGB(x, y)) > 0) {// 0
						numstr.append("0");						
					} else {// 1
						numstr.append("1");						
					}					
				}
				
				String str = new String();

				str = numstr.toString();
				if (!str.equals("00000000000000000000")) {
					b = true;
					if (str.equals(a01)) {
						a[0] = a[0] + 1;
					}
					if (str.equals(a00)) {
						a[0] = a[0] + 1;
					}
					if (str.equals(a02)) {
						a[0] = a[0] + 1;
					}
					if (str.equals(a03)) {
						a[0] = a[0] + 1;
					}
					if (str.equals(a04)) {
						a[0] = a[0] + 1;
					}
					if (str.equals(a05)) {
						a[0] = a[0] + 1;
					}
					if (str.equals(a06)) {
						a[0] = a[0] + 1;
					}
					if (str.equals(a07)) {
						a[0] = a[0] + 1;
					}
					if (str.equals(a08)) {
						a[0] = a[0] + 1;
					}
					if (str.equals(a09)) {
						a[0] = a[0] + 1;
					}

					if (str.equals(a10)) {
						a[1] = a[1] + 1;
					}
					if (str.equals(a11)) {
						a[1] = a[1] + 1;
					}
					if (str.equals(a12)) {
						a[1] = a[1] + 1;
					}
					if (str.equals(a13)) {
						a[1] = a[1] + 1;
					}
					if (str.equals(a14)) {
						a[1] = a[1] + 1;
					}
					if (str.equals(a15)) {
						a[1] = a[1] + 1;
					}
					if (str.equals(a16)) {
						a[1] = a[1] + 1;
					}
					if (str.equals(a17)) {
						a[1] = a[1] + 1;
					}
					if (str.equals(a20)) {
						a[2] = a[2] + 1;
					}
					if (str.equals(a21)) {
						a[2] = a[2] + 1;
					}
					if (str.equals(a22)) {
						a[2] = a[2] + 1;
					}
					if (str.equals(a23)) {
						a[2] = a[2] + 1;
					}
					if (str.equals(a24)) {
						a[2] = a[2] + 1;
					}
					if (str.equals(a25)) {
						a[2] = a[2] + 1;
					}
					if (str.equals(a26)) {
						a[2] = a[2] + 1;
					}
					if (str.equals(a27)) {
						a[2] = a[2] + 1;
					}
					if (str.equals(a28)) {
						a[2] = a[2] + 1;
					}
					if (str.equals(a30)) {
						a[3] = a[3] + 1;
					}
					if (str.equals(a31)) {
						a[3] = a[3] + 1;
					}
					if (str.equals(a32)) {
						a[3] = a[3] + 1;
					}
					if (str.equals(a33)) {
						a[3] = a[3] + 1;
					}
					if (str.equals(a34)) {
						a[3] = a[3] + 1;
					}
					if (str.equals(a35)) {
						a[3] = a[3] + 1;
					}
					if (str.equals(a36)) {
						a[3] = a[3] + 1;
					}
					if (str.equals(a37)) {
						a[3] = a[3] + 1;
					}
					if (str.equals(a40)) {
						a[4] = a[4] + 1;
					}
					if (str.equals(a41)) {
						a[4] = a[4] + 1;
					}
					if (str.equals(a42)) {
						a[4] = a[4] + 1;
					}
					if (str.equals(a43)) {
						a[4] = a[4] + 1;
					}
					if (str.equals(a44)) {
						a[4] = a[4] + 1;
					}
					if (str.equals(a45)) {
						a[4] = a[4] + 1;
					}
					if (str.equals(a46)) {
						a[4] = a[4] + 1;
					}
					if (str.equals(a47)) {
						a[4] = a[4] + 1;
					}
					if (str.equals(a48)) {
						a[4] = a[4] + 1;
					}
					if (str.equals(a49)) {
						a[4] = a[4] + 1;
					}
					if (str.equals(a401)) {
						a[4] = a[4] + 1;
					}
					if (str.equals(a50)) {
						a[5] = a[5] + 1;
					}
					if (str.equals(a51)) {
						a[5] = a[5] + 1;
					}
					if (str.equals(a52)) {
						a[5] = a[5] + 1;
					}
					if (str.equals(a53)) {
						a[5] = a[5] + 1;
					}
					if (str.equals(a54)) {
						a[5] = a[5] + 1;
					}
					if (str.equals(a55)) {
						a[5] = a[5] + 1;
					}
					if (str.equals(a56)) {
						a[5] = a[5] + 1;
					}
					if (str.equals(a57)) {
						a[5] = a[5] + 1;
					}
					if (str.equals(a60)) {
						a[6] = a[6] + 1;
					}
					if (str.equals(a61)) {
						a[6] = a[6] + 1;
					}
					if (str.equals(a62)) {
						a[6] = a[6] + 1;
					}
					if (str.equals(a63)) {
						a[6] = a[6] + 1;
					}
					if (str.equals(a64)) {
						a[6] = a[6] + 1;
					}
					if (str.equals(a65)) {
						a[6] = a[6] + 1;
					}
					if (str.equals(a66)) {
						a[6] = a[6] + 1;
					}
					if (str.equals(a67)) {
						a[6] = a[6] + 1;
					}
					if (str.equals(a68)) {
						a[6] = a[6] + 1;
					}
					if (str.equals(a69)) {
						a[6] = a[6] + 1;
					}
					if (str.equals(a70)) {
						a[7] = a[7] + 1;
					}
					if (str.equals(a71)) {
						a[7] = a[7] + 1;
					}
					if (str.equals(a72)) {
						a[7] = a[7] + 1;
					}
					if (str.equals(a73)) {
						a[7] = a[7] + 1;
					}
					if (str.equals(a74)) {
						a[7] = a[7] + 1;
					}
					if (str.equals(a75)) {
						a[7] = a[7] + 1;
					}
					if (str.equals(a76)) {
						a[7] = a[7] + 1;
					}
					if (str.equals(a77)) {
						a[7] = a[7] + 1;
					}
					if (str.equals(a78)) {
						a[7] = a[7] + 1;
					}
					if (str.equals(a80)) {
						a[8] = a[8] + 1;
					}
					if (str.equals(a81)) {
						a[8] = a[8] + 1;
					}
					if (str.equals(a82)) {
						a[8] = a[8] + 1;
					}
					if (str.equals(a83)) {
						a[8] = a[8] + 1;
					}
					if (str.equals(a84)) {
						a[8] = a[8] + 1;
					}

					if (str.equals(a85)) {
						a[8] = a[8] + 1;
					}

					if (str.equals(a86)) {
						a[8] = a[8] + 1;
					}
					if (str.equals(a87)) {
						a[8] = a[8] + 1;
					}
					if (str.equals(a88)) {
						a[8] = a[8] + 1;
					}
					if (str.equals(a89)) {
						a[8] = a[8] + 1;
					}
					if (str.equals(a90)) {
						a[9] = a[9] + 1;
					}
					if (str.equals(a91)) {
						a[9] = a[9] + 1;
					}
					if (str.equals(a92)) {
						a[9] = a[9] + 1;
					}
					if (str.equals(a93)) {
						a[9] = a[9] + 1;
					}
					if (str.equals(a94)) {
						a[9] = a[9] + 1;
					}
					if (str.equals(a94)) {
						a[9] = a[9] + 1;
					}
					if (str.equals(a95)) {
						a[9] = a[9] + 1;
					}
					if (str.equals(a96)) {
						a[9] = a[9] + 1;
					}
					if (str.equals(a97)) {
						a[9] = a[9] + 1;
					}
					if (str.equals(a98)) {
						a[9] = a[9] + 1;
					}
					if (str.equals(a99)) {
						a[9] = a[9] + 1;
					}
				} else {
					int max = a[0];
					for (int j = 0; j < 10; j++) {
						if (max <= a[j]) {
							max = a[j];
							index = j;
						}
					}					
					a[0] = 0;
					a[1] = 0;
					a[2] = 0;
					a[3] = 0;
					a[4] = 0;
					a[5] = 0;
					a[6] = 0;
					a[7] = 0;
					a[8] = 0;
					a[9] = 0;
				}
				if (b) {
					b = false;
					result.append(index);
				}
			}
			return result.toString();
		}

		public static int getGray(int rgb) {
			String str = Integer.toHexString(rgb);
			int r = Integer.parseInt(str.substring(2, 4), 16);
			int g = Integer.parseInt(str.substring(4, 6), 16);
			int b = Integer.parseInt(str.substring(6, 8), 16);
			// or 直接new个color对象
			Color c = new Color(rgb);
			r = c.getRed();
			g = c.getGreen();
			b = c.getBlue();
			int top = (r - 160) + (g - 160) + (b - 160);
			return (int) (top);
		}

}
