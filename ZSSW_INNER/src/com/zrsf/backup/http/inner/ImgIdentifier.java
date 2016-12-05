
package com.zrsf.backup.http.inner;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class ImgIdentifier {
	private static String a00 = "000000011111100000";
	private static String a01 = "000001111111110000";
	private static String a02 = "000111100000111000";
	private static String a03 = "000110000000011000";
	private static String a04 = "001100000000011000";
	private static String a05 = "001100000000110000";
	private static String a06 = "001100000001110000";
	private static String a07 = "001111111111100000";
	private static String a08 = "000111111110000000";

	private static String a10 = "000000000000011000";
	private static String a11 = "000000000000011000";
	private static String a12 = "000110000000011000";
	private static String a13 = "000110000000111000";
	private static String a14 = "000110111111111000";
	private static String a15 = "001111111111011000";
	private static String a16 = "001111000000011000";
	private static String a17 = "000000000000011000";

	private static String a20 = "000000000000011000";
	private static String a21 = "000000000000111000";
	private static String a22 = "000110000001111000";
	private static String a23 = "001100000010011000";
	private static String a24 = "001100000110011000";
	private static String a25 = "001100001100011000";
	private static String a26 = "001100011000011000";
	private static String a27 = "001110110000011000";
	private static String a28 = "000111110000000000";
	private static String a29 = "000011000000000000";

	private static String a30 = "000000000000110000";
	private static String a31 = "000000000000111000";
	private static String a32 = "000110011000011000";
	private static String a33 = "001100011000011000";
	private static String a34 = "001100011000011000";
	private static String a35 = "001100011000011000";
	private static String a36 = "001100011101110000";
	private static String a37 = "001110101111100000";
	private static String a38 = "000111100111000000";
	private static String a39 = "000111000000000000";

	private static String a40 = "000000000011000000";
	private static String a41 = "000000001111000000";
	private static String a42 = "000000011111000000";
	private static String a43 = "000000110011000000";
	private static String a44 = "000011100011111000";
	private static String a45 = "000110111111111000";
	private static String a46 = "001111111111000000";
	private static String a47 = "001111000011000000";
	private static String a48 = "000000000011000000";
	//
	private static String a50 = "000000000000110000";
	private static String a51 = "000000111000011000";
	private static String a52 = "001111111000011000";
	private static String a53 = "001111011000011000";
	private static String a54 = "001100011000011000";
	private static String a55 = "001100011000011000";
	private static String a56 = "001100011101110000";
	private static String a57 = "001100001111100000";
	private static String a58 = "001100000111000000";
	private static String a59 = "001100000000000000";

	private static String a60 = "000000011111100000";
	private static String a61 = "000001111111110000";
	private static String a62 = "000011101100011000";
	private static String a63 = "000110001000011000";
	private static String a64 = "001100011000011000";
	private static String a65 = "001100011000011000";
	private static String a66 = "001100011100110000";
	private static String a67 = "001100001111100000";
	private static String a68 = "000110000111000000";

	private static String a70 = "000000000000001000";
	private static String a71 = "001100000000111000";
	private static String a72 = "001100000011110000";
	private static String a73 = "001100000111000000";
	private static String a74 = "001100001100000000";
	private static String a75 = "001100111000000000";
	private static String a76 = "001101100000000000";
	private static String a77 = "001111000000000000";
	private static String a78 = "001110000000000000";

	private static String a80 = "000000000011110000";
	private static String a81 = "000000000111111000";
	private static String a82 = "000111101100011000";
	private static String a83 = "001111111000011000";
	private static String a84 = "001100011000011000";
	private static String a85 = "001100011100011000";
	private static String a86 = "001100111100110000";
	private static String a87 = "001111100111110000";
	private static String a88 = "000111000011000000";

	private static String a90 = "000000000000110000";
	private static String a91 = "000011111000011000";
	private static String a92 = "000111111100011000";
	private static String a93 = "001110001100011000";
	private static String a94 = "001100001100011000";

	private static String a95 = "001100001100110000";
	private static String a96 = "001100001001100000";
	private static String a97 = "001110111111000000";
	private static String a98 = "000111111110000000";

	public static String identify(InputStream in) {
		BufferedImage bi=null;
		try {
			bi = ImageIO.read(in);
		} catch (IOException e) {			
			e.printStackTrace();
			return null;
		}
		int h = bi.getHeight() - 2;
		int w = bi.getWidth() - 2;	
		StringBuffer result = new StringBuffer();
		int a[] = new int[10];
		a[0]=0;
		a[1]=0;
		a[2]=0;
		a[3]=0;
		a[4]=0;
		a[5]=0;
		a[6]=0;
		a[7]=0;
		a[8]=0;
		a[9]=0;
		boolean b=false;
		int index=0;
		for (int x = 1; x < w + 1; x++) {
			StringBuffer numstr = new StringBuffer();
			for (int y = 1; y < h + 1; y++) {
				if (getGray(bi.getRGB(x, y)) > 0) {// 0
					numstr.append("0");
				} else {
					numstr.append("1");
				}
			}
			String str = new String();
			
			str = numstr.toString();
			if (!str.equals("000000000000000000")) {
				b=true;
				if (str.equals(a01)) {
					a[0]=a[0]+1;
				}
				if (str.equals(a00)) {
					a[0]=a[0]+1;
				}
				if (str.equals(a02)) {
					a[0]=a[0]+1;
				}
				if (str.equals(a03)) {
					a[0]=a[0]+1;
				}
				if (str.equals(a04)) {
					a[0]=a[0]+1;
				}
				if (str.equals(a05)) {
					a[0]=a[0]+1;
				}
				if (str.equals(a06)) {
					a[0]=a[0]+1;
				}
				if (str.equals(a07)) {
					a[0]=a[0]+1;
				}
				if (str.equals(a08)) {
					a[0]=a[0]+1;
				}

				if (str.equals(a10)) {
					a[1]=a[1]+1;
				}
				if (str.equals(a11)) {
					a[1]=a[1]+1;
				}
				if (str.equals(a12)) {
					a[1]=a[1]+1;
				}
				if (str.equals(a13)) {
					a[1]=a[1]+1;
				}
				if (str.equals(a14)) {
					a[1]=a[1]+1;
				}
				if (str.equals(a15)) {
					a[1]=a[1]+1;
				}
				if (str.equals(a16)) {
					a[1]=a[1]+1;
				}
				if (str.equals(a17)) {
					a[1]=a[1]+1;
				}
				if (str.equals(a20)) {
					a[2]=a[2]+1;
				}
				if (str.equals(a21)) {
					a[2]=a[2]+1;
				}
				if (str.equals(a22)) {
					a[2]=a[2]+1;
				}
				if (str.equals(a23)) {
					a[2]=a[2]+1;
				}
				if (str.equals(a24)) {
					a[2]=a[2]+1;
				}
				if (str.equals(a25)) {
					a[2]=a[2]+1;
				}
				if (str.equals(a26)) {
					a[2]=a[2]+1;
				}
				if (str.equals(a27)) {
					a[2]=a[2]+1;
				}
				if (str.equals(a28)) {
					a[2]=a[2]+1;
				}
				if (str.equals(a29)) {
					a[2]=a[2]+1;
				}

				if (str.equals(a30)) {
					a[3]=a[3]+1;
				}
				if (str.equals(a31)) {
					a[3]=a[3]+1;
				}
				if (str.equals(a32)) {
					a[3]=a[3]+1;
				}
				if (str.equals(a33)) {
					a[3]=a[3]+1;
				}
				if (str.equals(a34)) {
					a[3]=a[3]+1;
				}
				if (str.equals(a35)) {
					a[3]=a[3]+1;
				}
				if (str.equals(a36)) {
					a[3]=a[3]+1;
				}
				if (str.equals(a37)) {
					a[3]=a[3]+1;
				}
				if (str.equals(a38)) {
					a[3]=a[3]+1;
				}
				if (str.equals(a39)) {
					a[3]=a[3]+1;
				}

				if (str.equals(a40)) {
					a[4]=a[4]+1;
				}
				if (str.equals(a41)) {
					a[4]=a[4]+1;
				}
				if (str.equals(a42)) {
					a[4]=a[4]+1;
				}
				if (str.equals(a43)) {
					a[4]=a[4]+1;
				}
				if (str.equals(a44)) {
					a[4]=a[4]+1;
				}
				if (str.equals(a45)) {
					a[4]=a[4]+1;
				}
				if (str.equals(a46)) {
					a[4]=a[4]+1;
				}
				if (str.equals(a47)) {
					a[4]=a[4]+1;
				}
				if (str.equals(a48)) {
					a[4]=a[4]+1;
				}

				if (str.equals(a50)) {
					a[5]=a[5]+1;
				}
				if (str.equals(a51)) {
					a[5]=a[5]+1;
				}
				if (str.equals(a52)) {
					a[5]=a[5]+1;
				}
				if (str.equals(a53)) {
					a[5]=a[5]+1;
				}
				if (str.equals(a54)) {
					a[5]=a[5]+1;
				}
				if (str.equals(a55)) {
					a[5]=a[5]+1;
				}
				if (str.equals(a56)) {
					a[5]=a[5]+1;
				}
				if (str.equals(a57)) {
					a[5]=a[5]+1;
				}
				if (str.equals(a58)) {
					a[5]=a[5]+1;
				}
				if (str.equals(a59)) {
					a[5]=a[5]+1;
				}

				if (str.equals(a60)) {
					a[6]=a[6]+1;
				}
				if (str.equals(a61)) {
					a[6]=a[6]+1;
				}
				if (str.equals(a62)) {
					a[6]=a[6]+1;
				}
				if (str.equals(a63)) {
					a[6]=a[6]+1;
				}
				if (str.equals(a64)) {
					a[6]=a[6]+1;
				}
				if (str.equals(a65)) {
					a[6]=a[6]+1;
				}
				if (str.equals(a66)) {
					a[6]=a[6]+1;
				}
				if (str.equals(a67)) {
					a[6]=a[6]+1;
				}
				if (str.equals(a68)) {
					a[6]=a[6]+1;
				}
				if (str.equals(a70)) {
					a[7]=a[7]+1;
				}
				if (str.equals(a71)) {
					a[7]=a[7]+1;
				}
				if (str.equals(a72)) {
					a[7]=a[7]+1;
				}
				if (str.equals(a73)) {
					a[7]=a[7]+1;
				}
				if (str.equals(a74)) {
					a[7]=a[7]+1;
				}
				if (str.equals(a75)) {
					a[7]=a[7]+1;
				}
				if (str.equals(a76)) {
					a[7]=a[7]+1;
				}
				if (str.equals(a77)) {
					a[7]=a[7]+1;
				}
				if (str.equals(a78)) {
					a[7]=a[7]+1;
				}

				if (str.equals(a80)) {
					a[8]=a[8]+1;
				}
				if (str.equals(a81)) {
					a[8]=a[8]+1;
				}
				if (str.equals(a82)) {
					a[8]=a[8]+1;
				}
				if (str.equals(a83)) {
					a[8]=a[8]+1;
				}

				if (str.equals(a84)) {
					a[8]=a[8]+1;
				}

				if (str.equals(a85)) {
					a[8]=a[8]+1;
				}

				if (str.equals(a86)) {
					a[8]=a[8]+1;
				}
				if (str.equals(a87)) {
					a[8]=a[8]+1;
				}
				if (str.equals(a88)) {
					a[8]=a[8]+1;
				}
				if (str.equals(a90)) {
					a[9]=a[9]+1;
				}
				if (str.equals(a91)) {
					a[9]=a[9]+1;
				}
				if (str.equals(a92)) {
					a[9]=a[9]+1;
				}
				if (str.equals(a93)) {
					a[9]=a[9]+1;
				}
				if (str.equals(a94)) {
					a[9]=a[9]+1;
				}
				if (str.equals(a94)) {
					a[9]=a[9]+1;
				}
				if (str.equals(a95)) {
					a[9]=a[9]+1;
				}
				if (str.equals(a96)) {
					a[9]=a[9]+1;
				}
				if (str.equals(a97)) {
					a[9]=a[9]+1;
				}
				if (str.equals(a98)) {
					a[9]=a[9]+1;
				}

			} else {
				int max = a[0];
				for (int j = 0; j < 10; j++) {
					if (max <=a[j])
					{
						max = a[j];
					    index=j;
					}
				}
				if(b)
				{
				b=false;
				result.append(index);
				}
				a[0]=0;
				a[1]=0;
				a[2]=0;
				a[3]=0;
				a[4]=0;
				a[5]=0;
				a[6]=0;
				a[7]=0;
				a[8]=0;
				a[9]=0;
			}
		}
		return result.toString();
	}

	public static int getGray(int rgb) {
		String str = Integer.toHexString(rgb);
		int r = Integer.parseInt(str.substring(2, 4), 16);
		int g = Integer.parseInt(str.substring(4, 6), 16);
		int b = Integer.parseInt(str.substring(6, 8), 16);		
		Color c = new Color(rgb);
		r = c.getRed();
		g = c.getGreen();
		b = c.getBlue();
		int top = (r - 160) + (g - 160) + (b - 160);
		return (int) (top);
	}

}
