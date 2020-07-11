
from src.main.templating import gen_file, template_conf
#### VARIABLES ####

user = 'toto'
worker_processes = 2
error_log = 'logs/error.log'
pid = 'logs/nginx.pid'
worker_rlimit_nofile = 42
args = {'user': user, 'worker_processes': worker_processes,
        'error_log': error_log, 'pid': pid, 'worker_rlimit_nofile': worker_rlimit_nofile}

####   SCRIPT  ####

print('## Méthode peu robuste')
print(gen_file(user, worker_processes=worker_processes,
               worker_rlimit_nofile=worker_rlimit_nofile))

print('## Méthode un peu plus robuste mais du coup sensible aux accolades')
with open('data/template_nginx.conf', 'r') as template:
    print(template_conf(template.read(), args))
