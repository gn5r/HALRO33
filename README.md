# チーム逆万枚就プレ企画

更新状況
- 2017年11月11日 リポジトリ構成見直し、プロジェクト統一

デザイン面でお二人にやって頂くかもしれないのでよろしく。<br>
ブランチ名は各自で好きなように設定して構わないけど、３人がわかる範囲内でおねがい<br>
基本的に僕のブランチ名は`gn5r`です

## Gitの流れ
<div align="center">
<img src="./img/project_image.jpg" alt="git操作イメージ">
</div>

## Gitコマンド使い方(基礎部分)

### Git初期設定〜ダウンロード編

まずGitリポジトリ(ディレクトリ)をダウンロードするフォルダを予め作成しておく<br>
一例ではLinux環境なので適宜読み替えてください。<br>
`/home/gn5r/←カレントディレクトリ $ mkdir Test`<br>
カレントディレクトリを作成したフォルダへ移動<br>
`cd Test/`<br>
Gitを使うため初期化<br>
`git init`<br>
Gitリポジトリを追加しダウンロード<br>
`git remote add name(好きな名前。originが基本) 該当URL`<br>
`git pull main ダウンロードするブランチ名(基本gn5r)`<br>
これでmasterブランチのファイル群がダウンロードできた<br>

### 内容変更〜アップロード編

アップロードの前にブランチ名を新規作成してもらう必要がある<br>
`git checkout -b name(ここは好きな名前でおｋ)`<br>
これができたら、ダウンロード編でダウンロードしたファイルを変更、修正した場合は、gitへアップロード<br>
`git add .`　を打つことで変更した物**全て**をアップロード対象にする<br>
`git commit -m "change xx.txt"` ←""の中に、何を変更したか等を明記<br>
コミットはどこを変更、修正などをしたかのコメントと思って頂けたらいいかも<br>
`git push リモート名 name(さっき作成したブランチ名)`<br>
これで晴れてアップロードが完了する

### 変更内容を master branch に適用させる

適用させる前に、適用していいかどうかをmasterに見てもらいます。<br>
**issue** という課題のようなものを提示しておいたので、まずそれを参照します。<br>
- github web上から **Pull Rewuests** をクリック
- 画面左側にある**new pull request**を選択
- 画面中央付近に**base master ... compare:branch name**があるので自身のブランチ名を選択(規定で自身のブランチが選択されていると思う)
- 変更・修正部分を明記する(issueの番号も明記 **#1** など また、Markdown形式なので改行など注意)
- **create pull request** をクリックしてリクエスト送信

以上を行いmasterがリクエストを許可すれば、変更・修正内容がmasterに適用される。

## Git&shellコマンド集

| Command Name     | 説明     |
| :------------: | :------------: |
| ls | カレントディレクトリにあるファイル一覧表示 |
| ls -a | カレントディレクトリにある隠しフォルダ含めすべて表示 |
| pwd | カレントディレクトリの表示(確認)　|
| cd | カレントディレクトリの移動 |
| cd / | rootディレクトリへ移動 |
| mkdir | 新規ディレクトリ作成 |
| touch | 新規ファイル(拡張子込)作成 |
| cp コピー対象 コピー場所 | ファイル/フォルダのコピー |

gitコマンドについては全て`git 〜`のコマンドです

| Git Command Name     | 説明     |
| :------------: | :------------: |
| init | 初期化|
| remote add name URL | リモートリポジトリ追加|
| pull name master(branch name)| マスターブランチからpull |
| checkout -b name | 新規ブランチ作成|
| add file name(拡張子付き) | アップロード対象を１ファイル(1フォルダ)に |
| add . | 全てアップロード対象 |
| commit -m "comment" | アップロード時のコメント&対象確定 |
| push name branch | リモートリポジトリへ対象をアップロード |
| status | 変更の状態を見れる(何が変更されたかなど)|
| remote -v      | リモートURLの確認 |
| remote remove name | nameで設定したURL削除 |

なお、Cmderの使い方として**矢印⇧**で履歴が使える<br>
**TABキー** であらゆる候補がでる(長いブランチ名やディレクトリ名の時に一々て入力せずに済む)<br>
ユーザー名・パスワード保存:`git config --global credential.helper store(wincred)`windowsはstoreの部分をwincredと打ち込めばおｋ<br>
何かわからなければ僕に聞いてください.
