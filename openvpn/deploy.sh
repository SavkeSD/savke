#!/bin/bash

set -e

echo -e "\n"


help_message="
Usage: \n
    ./deploy.sh -u <user> -i <networkInterface> -e <prod> \n

    Where user flags are: \n
    - u - user with sudo privileges on server, mandatory option \n
    - i - default network interface on OVPN server, mandatory option \n
    - e - e is environment, mandatory option \n
    - h - help, no option \n
    "

if [ $# -eq 0 ];
then
    echo -e ${help_message}
    exit 1
else
    ansible_user=sanity
    playbook=VPN-final-playbook.yaml

    while getopts "u:i:e:h" optname
    do
        case "${optname}" in
          "u")
              ansible_user=${OPTARG}
              ;;
          "i")
	      ethernetInterface=${OPTARG}
              ;;	
          "e")
              if [ ${OPTARG} = "prod" ]; then
                    environment=${OPTARG}
              else
                  echo "Invalid environment"
                  echo ${help_message}
                  exit 1
              fi
              ;;
          "h")
              echo -e ${help_message}
              exit 0
              ;;
          "?")
              echo -e ${help_message}
              exit 0
              ;;
        esac
    done
    if [ $ansible_user = "sanity" ]; then
        echo "Please use valid user!"
        echo "$help_message"
        exit 1
    elif [ -z $environment ]; then
        echo "Please select environment!"
        echo "$help_message"
        exit 1
    fi
	
    echo "Executing ansible with following parameters:"
    echo "ansible-playbook -i ovpn-${environment} ${playbook} --user=${ansible_user} --ask-pass --become --ask-become-pass --ask-vault-pass  --extra-vars \"ethernetInterface=${ethernetInterface}\""

    sleep 5

    ansible-playbook -i ovpn-${environment} ${playbook}  \
	    --user=${ansible_user} --ask-pass --become --ask-become-pass --ask-vault-pass --extra-vars "ethernetInterface=${ethernetInterface}"
fi
