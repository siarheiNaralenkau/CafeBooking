import os
from os import listdir
from os.path import isfile, join

sql_path = './sqls'
command = 'mysql --host=localhost  --user=admin --password=qwerty12Q --database=bronimesto < {0}'

files = [f for f in listdir(sql_path) if isfile(join(sql_path, f))]
files.sort()
for f in files:
    f_command = command.format(sql_path + '/' + f)
    print f_command
    os.system(f_command)


