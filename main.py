
import re
import requests
url="https://music.163.com/discover/toplist?id=3778678"
response=requests.get(url=url,verify=True)
html_data=response.text
song_list=re.findall('<li><a href="/song\?id=(.*?)">(.*?)</a></li>',html_data)
print(song_list)

for song in song_list:
    song_url = 'https://music.163.com/song/media/outer/url?id=' + song[0]
    print(song[1],song_url)
    print(song[0])
    mp3_url = song_url
    mp3_data = requests.get(mp3_url).content
    with open(f'{song[1]}.mp3', mode='wb') as f:
        f.write(mp3_data)
    print(song[1], '歌曲下载成功')
