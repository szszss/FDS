# FDS - Finite Digit Summator

[![Build Status](https://travis-ci.org/szszss/FDS.svg?branch=master)](https://travis-ci.org/szszss/FDS)

**CAUTION: WIP**

**Finite Digit Summator** (**FDS** for short) is a "boring" project which could either parse or generator lots of math expressions and evaluate 'em all.

```
　　　　　　　　　 ,○､.,　　 　 ,○､.,_
　　　　　　　　,.: '"ﾄ､ヽ   .／ 　 ',ヽ.,
　　　　　　 ／　ｒ-i___`''ｰ/_」 　　iヽ､_,＞
　　　　 　/_　　'"´　 ￣｀"' ___「`'|
　　 　　 ,'_」-ｧ‐-r‐-v-､__,.!､_｀ヾ!
　　　 _ｒ'-'!ｧ''"´ ￣￣｀"'' -'､.,>､ﾊ
　　 r'7ｧ'´　　　/　 ヽ.　!　　  ヽ_」'､
　 　Y　 ／   ,_/_i　  ﾊ-'r-',　 　Yヽ〉　　ﾄ､,,.. -‐ｧ''ア´      Finite♂Digit♂Summator
　 　,'　/　 / 」_ﾊ　 /ｧ;'‐=;'､! 　 ! !',.. -/　':,　　Y´　   An industrial-age machine,
　　 |.　| 　! |､ 」`V  !_,r!ﾊ,.ｲ i　 ,:'ヽﾍ.,i 　/,. -r‐ｧ which always give you lemon-lime
　　 ',　',　ﾊ. ゞ` 　 ｀-´ /　/　,:'／ 　　　.!／　　 Y         when you want orange.
　　　ヽﾍ､ ,ゞ　 　|￣`\　∠_／ ,.ｲ /　    　　 | 　 　 !
　　　　 ,ﾊ　｀|ｧ ､`___,.イ ハへ(ゝ'　　　　  　! 　　　 |
　　　  ､',__ ゝへ !イﾄ-‐イ/l´｀ヽ.'7　 　  　  i　 　   ,'
　　　　｀ヽ|　/  　!7'ｰ"i_」__   i　         ,' 　   ,'/--‐ｧ
　　　　 ／', ! _,,.!-rｧr'i´　　 ` |/　     ,' 　　 ,へ､_,/
　　　 ./　/ '!　　　| l　|　　　 ,ｲ      ／　   ／　　 ,:'
　　 　 ! .,'　,ゝ､.,__」_」_」,‐ｧ'ハ､_,.イ_.-＜　　  ／
　　　　ヽ! 　 　　| | l::::r'7/ ' 　 ヽ.ﾄ､!　　　｀''く.
　　　　　ヽ､,ゝ､/ | l::ノ:,' i　 ',　　 ｀>､　　　　 　 ',
　　 　 　 ∠r'Y　 | |:(･∀i  |　　 ',　_,.ｲ/､ 　　　 ,.ｒ'!
　　　　　　　ヽﾄ､_|_!___;:」」__,,..r,ﾍンi｀'' ｰ-＜　   !
　　　　　　　　 `└ｒ-'-ﾄ--'ー'^ヽ､_,!　　　　　  　 ｀ヽ!
　　　　　　　　 　　 '､._,!
```

### Features
 
- **Pure Java** : No JNI. No platform dependent. Written in Java6. It can running on any platform as long as it supports Java.
- **Lightweight** : Such damn small, pal!
- **Modular** : A simple core and several applications. 
- **Open Source** : Open Source, with a permissive license: MIT-License.
- **Déjà vu** : Well... This intro is copied from another project of mine :)

### Modules & Products

- **FDS - core** : A minimum core library.
- **FDS - cmd** : A terminal of FDS without graphics.
- **FDS - desktop** : A terminal of FDS with graphics. Powered by Swing.
- **FDS - web** : What about solving math questions on browser? Powered by JFinal.
- **FDS - android** : Solve math questions on your phone. Ha.

```
　　　　　　　　　　　　　　 　   __
　　　　　　　　　 　 　 　 　 　(圭)￣￣￣￣＼
　　　　 　 　 　   __　　　／l二) 八＼/＼　 　＼
　　　　　　　　   (圭)￣￣/　　 {　∧　　(≧=-　〈_}￣ヽ／l
　　　　　　　　    八 ` ＜￣＞　乂__ﾉ　　 ＼　　　/　 /｀ l
　　 　 　 　 　   lハ 、 (￣￣ヽ__　　　　　　　　　 / ＼ }
　　　　　　　   　从ﾉ )　 ＼     / ⌒\__r―､___ _ノ＼/   l¨
　　　　　　　　　　　　　　  /＼(⌒ヽ{　{---┤__)--ﾑ_〉 l  
　　　　　　　　　　　 　 　 {　　ﾑ、_ﾄ⌒ l＼___ ‘，|　/ll
　　　　 　 　 　 　 　 　 　 ヽ__ﾊ Vﾘ   丶ゝﾞてﾘ｀}/ / ∥
　　　　　　　　　　　　　 　 　 ﾉ从　　'　　  _彡/　/(
　　　　　　　　　　　　　　　　　　个s｀ ‐'　イ//l/／
　　　 　 　 　 　 　 　 　 　 　 ＞{¨〈_〕Ii´／l {-_＞ｧ
　　　　　　　　　　　 　 　 　 /　 乂h-_-∨_-_-_-／)ﾆ≧、
　　　　　　　　　　　　　　　 /　 /八_ﾊ　ｏヽ_-／)ﾆ/／ }
　 　 　 　 　 　 　 　   　 ﾉ＞'`'”ﾆﾆ乂＼__ﾉ-}ニ//　　/
　　　　　　　　　　　　　　/ニニニニﾉ乂八_ノニニ{/ 　 /
　　　　　　　 　 　 　 　 ｲニニニニﾆ／ニニニニﾆﾆ}　 ∧
　　　　　　　　 　 　 　 八ニニニニ/ニニニニニニﾉ　 ∥ ﾞ
　　　　　　　　　　　　/_/≧=- ニノニニニニニﾆノ 　∥  l
　　　　　　　　　　　 /_/-_-_/_-_-_≧=-----＜⌒  ∥∧. l　　　　　　. .-―-. . __ノﾉ
　　　　　 ／　 　 　  V_-_-_{_-_-_-/_(　　 　　 ∥ニ∧l　　　　／⌒: : : : : : :＞
　　　　〃　　　 　 　  乂.-_-{-_-_-/　)　　 　 ∥ニニﾑ＜⌒/: : :／: : : : : : : : : :＼
　　　/ /　　　　　　　　 ￣乂_-{⌒(　　　 　 ノニニニﾑﾆﾑ/: : /,. -‐…・・…‐-: 、丶
　　 /　{　　　　　　　　　　　 　 ￣≧=--=≦ニニニニニ// ＼/: : : : : : : : : : : : : : :＼:
　　 {　　、 　 　 　 　 　 ,.　-――　∨ニニニニニニニ//　 　 ヽ : : : : : : : :＼: : : : : :
　　 {　　 ＼　　　　-＜: : : : : : :-‐Ｖニニニニニﾆﾆ// 　 　  `、 : : : : : : : ＼: : : :
　　 ﾄ . . . . : :￣: : : :／:  : : : :  Vニニニニニﾆ//　　　　　  `、: : : : : : : : 丶: :
　 　 : :＼: : : : :: : ／ : : : : : : : :}ニニニニニﾆ∥　　　　　　 　、: : :| : : : : : : : :
　　 乂 : : : : : : ／: : : : : : : : : : :|ニニニニニﾆ| |　 　 　 　 　 、: / : : : : : : : :
　　　　＞‐: : .:／: : : : : : : : : : : : {ニニニニニﾆ| |　 　 　 　 　   } /: : : : : : : : :}:
　　／⌒丶 : : ／: : : : : : : : : : : : : :lニニニニﾆﾆ=| |　 　 　 　  　 ﾚ : : : : : : : : /:
　/ 　 　 　／ : : : : : : : : : : : : : : ｌニニニニﾆﾆ=| |　　　　　　 　/ : : : : : : : : /: :
　　,. : :⌒/: : : : : : : : : : : : : : : : ﾏニニニニﾆﾆ| |　　　　　　 ／ : : : : : : : : /: : :
.／: : : : /: : : : : : : : : : : : : : : : :.:ﾏニニニニニ| |　　　　／: : ／: : : : : :／: : : :
: : : : : :/: : :／: : : : : : : : :／: : : : : ﾏニニニニﾆ| |　　 ／: : ／: : : : : :／: : : :／:
: : : : : {: ／: : : : : : : : : :／ : : : : : : }ニニニﾆﾆ=| |　／: :／: : : : : :／: : : :-=≦:
: : : : :／: : : : : : : : : : :/: : : : : : : .|ニニニﾆﾆ=| l／ : : : : : : : : ／: :／ : : : : : : :
: : : /: : : : : : : : : : : :./: : : : : : : :.|ニニニニ=／: : : : : : : : :／: : :/: : : : : : : : : :

                  [Ran]
                  [And you know you will be defeated!]
                  
                                Persuade
                                    ↑
                        Sarcastic ←   →  Attack
                                    ↓
             "I know your UNATCO killphrase: Laputan machine"
```
