package com.zrsf.common.util;

import java.util.HashMap;
import java.util.Map;
/**
 * 转义字符的转换
 * 
 * @author Terry
 *
 */
public class HtmlCodeMap {
	
	public static Map<String,String> htmlCodeMap=null;//转义字符和实体的map结构
	public static ObjectarrToStringarr stringarr = new ObjectarrToStringarr();
	public static String[] htmlcode = null;
	public static String[] sign = null;
	
	static{
		HtmlCodeMap.init();
	}
	public HtmlCodeMap(){
		super();		
	}
	
	public static void init(){		
		htmlCodeMap=new HashMap<String,String>();		
		htmlCodeMap.put("&Alpha;","Α");
		htmlCodeMap.put("&Delta;","Δ");
		htmlCodeMap.put("&Eta;","Η");
		htmlCodeMap.put("&Kappa;","Κ");
		htmlCodeMap.put("&Nu;","Ν");
		htmlCodeMap.put("&Pi;","Π");
		htmlCodeMap.put("&Tau;","Τ");
		htmlCodeMap.put("&Chi;","Χ");
		htmlCodeMap.put("&alpha;","α");
		htmlCodeMap.put("&delta;","δ");
		htmlCodeMap.put("&eta;","η");
		htmlCodeMap.put("&kappa;","κ");
		htmlCodeMap.put("&nu;","ν");
		htmlCodeMap.put("&pi;","π");
		htmlCodeMap.put("&sigma; ","σ");
		htmlCodeMap.put("&phi;","φ");
		htmlCodeMap.put("&omega;","ω");
		htmlCodeMap.put("&piv;","ϖ");
		htmlCodeMap.put("&prime;","′");
		htmlCodeMap.put("&frasl;","⁄");
		htmlCodeMap.put("&real;","ℜ");
		htmlCodeMap.put("&larr;","←");
		htmlCodeMap.put("&darr;","↓");
		htmlCodeMap.put("&lArr;","⇐");
		htmlCodeMap.put("&dArr;","⇓");
		htmlCodeMap.put("&part;","∂");
		htmlCodeMap.put("&nabla;","∇");
		htmlCodeMap.put("&ni;","∋");
		htmlCodeMap.put("&minus;","−");
		htmlCodeMap.put("&prop;","∝");
		htmlCodeMap.put("&and;","∧");
		htmlCodeMap.put("&cup;","∪");
		htmlCodeMap.put("&sim;","∼");
		htmlCodeMap.put("&ne;","≠");
		htmlCodeMap.put("&ge;","≥");
		htmlCodeMap.put("&nsub;","⊄");
		htmlCodeMap.put("&oplus;","⊕");
		htmlCodeMap.put("&sdot;","⋅");
		htmlCodeMap.put("&lfloor;","『");
		htmlCodeMap.put("&spades;","♠");
		htmlCodeMap.put("&diams;","♦");
		htmlCodeMap.put("&cent;","¢");
		htmlCodeMap.put("&yen;","¥");
		htmlCodeMap.put("&uml;","¨");
		htmlCodeMap.put("&laquo;","«");
		htmlCodeMap.put("&reg;","®");
		htmlCodeMap.put("&plusmn;","±");
		htmlCodeMap.put("&acute;","´");
		htmlCodeMap.put("&lt;","<");
		htmlCodeMap.put("&Beta;","Β");
		htmlCodeMap.put("&Epsilon;","Ε");
		htmlCodeMap.put("&Theta;","Θ");
		htmlCodeMap.put("&Lambda;","Λ");
		htmlCodeMap.put("&Xi;","Ξ");
		htmlCodeMap.put("&Rho;","Ρ");
		htmlCodeMap.put("&Upsilon;","Υ");
		htmlCodeMap.put("&Psi;","Ψ");
		htmlCodeMap.put("&beta;","β");
		htmlCodeMap.put("&epsilon;","ε");
		htmlCodeMap.put("&theta;","θ");
		htmlCodeMap.put("&lambda;","λ");
		htmlCodeMap.put("&xi;","ξ");
		htmlCodeMap.put("&rho;","ρ");
		htmlCodeMap.put("&tau;","τ");
		htmlCodeMap.put("&chi;","χ");
		htmlCodeMap.put("&thetasym;","ϑ");
		htmlCodeMap.put("&bull;","•");
		htmlCodeMap.put("&Prime;","″");
		htmlCodeMap.put("&weierp;","℘");
		htmlCodeMap.put("&trade;","™");
		htmlCodeMap.put("&uarr;","↑");
		htmlCodeMap.put("&harr;","↔");
		htmlCodeMap.put("&uArr;","⇑");
		htmlCodeMap.put("&hArr;","⇔");
		htmlCodeMap.put("&exist;","∃");
		htmlCodeMap.put("&isin;","∈");
		htmlCodeMap.put("&prod;","∏");
		htmlCodeMap.put("&lowast;","∗");
		htmlCodeMap.put("&infin;","∞");
		htmlCodeMap.put("&or;","∨");
		htmlCodeMap.put("&int;","∫");
		htmlCodeMap.put("&cong;","≅");
		htmlCodeMap.put("&equiv;","≡");
		htmlCodeMap.put("&sub;","⊂");
		htmlCodeMap.put("&sube;","⊆");
		htmlCodeMap.put("&otimes;","⊗");
		htmlCodeMap.put("&lceil;","『");
		htmlCodeMap.put("&rfloor;","』");
		htmlCodeMap.put("&clubs;","♣");
		htmlCodeMap.put("&nbsp;"," ");
		htmlCodeMap.put("&pound;","£");
		htmlCodeMap.put("&brvbar;","¦");
		htmlCodeMap.put("&copy;","©");
		htmlCodeMap.put("&not;","¬");
		htmlCodeMap.put("&macr;","¯");
		htmlCodeMap.put("&sup2;","²");
		htmlCodeMap.put("&micro;","µ");
		htmlCodeMap.put("&gt;",">");
		htmlCodeMap.put("&Gamma;","Γ");
		htmlCodeMap.put("&Zeta;","Ζ");
		htmlCodeMap.put("&Iota;","Ι");
		htmlCodeMap.put("&Mu;","Μ");
		htmlCodeMap.put("&Omicron;","Ο");
		htmlCodeMap.put("&Sigma;","Σ");
		htmlCodeMap.put("&Phi;","Φ");
		htmlCodeMap.put("&Omega;","Ω");
		htmlCodeMap.put("&gamma;","γ");
		htmlCodeMap.put("&zeta;","ζ");
		htmlCodeMap.put("&iota;","ι");
		htmlCodeMap.put("&mu;","μ");
		htmlCodeMap.put("&omicron;","ο");
		htmlCodeMap.put("&sigmaf;","ς");
		htmlCodeMap.put("&upsilon;","υ");
		htmlCodeMap.put("&psi;","ψ");
		htmlCodeMap.put("&upsih;","ϒ");
		htmlCodeMap.put("&hellip;","…");
		htmlCodeMap.put("&oline;","‾");
		htmlCodeMap.put("&image;","ℑ");
		htmlCodeMap.put("&alefsym;","ℵ");
		htmlCodeMap.put("&rarr;","→");
		htmlCodeMap.put("&crarr;","↵");
		htmlCodeMap.put("&rArr;","⇒");
		htmlCodeMap.put("&forall;","∀");
		htmlCodeMap.put("&empty;","∅");
		htmlCodeMap.put("&notin;","∉");
		htmlCodeMap.put("&sum;","∑");
		htmlCodeMap.put("&radic;","√");
		htmlCodeMap.put("&ang;","∠");
		htmlCodeMap.put("&cap;","∩");
		htmlCodeMap.put("&there4;","∴");
		htmlCodeMap.put("&asymp;","≈");
		htmlCodeMap.put("&le;","≤");
		htmlCodeMap.put("&sup;","⊃");
		htmlCodeMap.put("&supe;","⊇");
		htmlCodeMap.put("&perp;","⊥");
		htmlCodeMap.put("&rceil;","』");
		htmlCodeMap.put("&loz;","◊");
		htmlCodeMap.put("&hearts;","♥");
		htmlCodeMap.put("&iexcl;","¡");
		htmlCodeMap.put("&curren;","¤");
		htmlCodeMap.put("&sect;","§");
		htmlCodeMap.put("&ordf;","ª");
		htmlCodeMap.put("&shy;","");
		htmlCodeMap.put("&deg;","°");
		htmlCodeMap.put("&sup3;","³");
		htmlCodeMap.put("&quot;","\"");
		htmlCodeMap.put("&ldquo;","“");
		htmlCodeMap.put("&rdquo;","”");
		htmlCodeMap.put("&mdash;","—");
		htmlCodeMap.put("&ndash;","–");
		htmlCodeMap.put("&permil;","‰");
		htmlCodeMap.put("&amp;", "&");
		htmlCodeMap.put("&lsquo;", "‘");
		htmlCodeMap.put("&sbquo;", "‚");
		htmlCodeMap.put("&rsquo;", "’");
		htmlCodeMap.put("&bdquo;", "„");
		htmlCodeMap.put("&middot;", "·");
		htmlCodeMap.put("&rsaquo;", "›");
		htmlCodeMap.put("&circ;", "ˆ");
		htmlCodeMap.put("&lsaquo;", "‹");
		htmlCodeMap.put("&times;", "×");
		htmlCodeMap.put("&divide;", "÷");
		htmlCodeMap.put("&Oslash;", "Ø");
		htmlCodeMap.put("&nbsp;", " ");
		
		htmlcode = stringarr.toStringarr(HtmlCodeMap.htmlCodeMap
				.keySet().toArray());
		sign = stringarr.toStringarr(HtmlCodeMap.htmlCodeMap.values()
				.toArray());
	}
	

}
