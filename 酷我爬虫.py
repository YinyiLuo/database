import requests
import prettytable as pt

headers = {
    'Cookie': 'Hm_lvt_cdb524f42f0ce19b169a8071123a4797=1666090836; _ga=GA1.2.1848965084.1666090836; _gid=GA1.2.276810660.1666090836; _gat=1; Hm_lpvt_cdb524f42f0ce19b169a8071123a4797=1666092934; kw_token=X0ZNXN6HR69',
    'csrf': 'X0ZNXN6HR69',
    'Host': 'www.kuwo.cn',
    'Referer': 'https://kuwo.cn/search/list?key=%E5%91%A8%E6%9D%B0%E4%BC%A6',
    'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/106.0.0.0 Safari/537.36 Edg/106.0.1370.42'
}


searchKey = input("输入歌手名或歌曲名：")
i=input("页码：")
url=f'https://kuwo.cn/api/www/search/searchMusicBykeyWord?key={searchKey}&pn='+i+'&rn=30&httpsStatus=1&reqId=0944ffa0-4ed9-11ed-9de9-9bd277df2447'


song_list=requests.get(url,headers=headers).json()['data']['list']
tb= pt.PrettyTable()
tb.field_names=['序号','歌名','歌手名','专辑']
info_list=[]
count=0
for song in song_list:
    rid = song['rid']
    singer_name = song['artist']
    song_name=song['name']
    album_name=song['album']
    info_list.append([rid,song_name,singer_name])
    tb.add_row([count,song_name,singer_name,album_name])
    count+=1
print(tb)

while(True):
    input_index = eval(input('输入想下载的歌曲序号（-1退出）'))
    if input_index==-1:
        break
    download_info=info_list[input_index]
    response = requests.get(f'https://kuwo.cn/api/v1/www/music/playUrl?mid={download_info[0]}&type=music&httpsStatus=1&reqId=30854fb1-4edf-11ed-8913-23459d0bd8ff',headers=headers)
    print(response.text)
    mp3_url=response.json()['data']['url']
    mp3_data =requests.get(mp3_url).content
    with open(f'{download_info[1]}-{download_info[2]}.mp3',mode='wb') as f:
        f.write(mp3_data)
    print(download_info[1],'歌曲下载成功')
