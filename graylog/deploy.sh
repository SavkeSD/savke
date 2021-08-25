#!/bin/bash


set -e

printf "\n"


help_message="
Usage: \n
    ./deploy.sh -u <user> -e <prod> -i <graylog||sidecar||sidecar-win> \n
\n
Where user flags are: \n
    - u - user with sudo privileges on server, mandatory option \n
    - e - e is environment, mandatory option \n
    - i - i is for install, what to install, graylog(linux), sidecar(linux) or sidecar-win(windows) 
    - h - help, no option \n
    "

if [ $# -eq 0 ];
then
    echo -e ${help_message}
    exit 1
else
    ansible_user=sanity
    playbook=sanity-playbook.yaml

    while getopts "u:e:h:i:" optname
    do
        case "${optname}" in
          "u")
              ansible_user=${OPTARG}
              ;;
          "e")
              if [ ${OPTARG} = "prod" ]; then
                   environment=${OPTARG}
              else
                  echo -e "Invalid environment \n"
                  echo ${help_message}
                  exit 1
              fi
              ;;
          "i")
              if [ ${OPTARG} = "graylog" ] || [ ${OPTARG} = "sidecar" ] || [ ${OPTARG} = "sidecar-win" ]; then
                   install=${OPTARG}
		           playbook=${install}-playbook.yaml
              else
                  echo -e "Invalid install option \n"
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
	
    case "${install}" in 
        # Here we have two cases, bcs sidecar for windows dont have options
        # --become and --ask-become-pass, and therefore running playbook is different
        "graylog" | "sidecar")
            echo "Executing ansible with following parameters:"
            echo "ansible-playbook -i ${install}-${environment} ${playbook} --user=${ansible_user} --ask-pass --become --ask-become-pass --ask-vault-pass"
            sleep 5

            ansible-playbook -i ${install}-${environment} ${playbook}  \
	             --user=${ansible_user} --ask-pass --become --ask-become-pass --ask-vault-pass
        ;;
        "sidecar-win")
            playbook=sidecar-playbook.yaml  # overriding playbook variable for win
                                                # that can be one playbook user for linux and win machine
            echo "Executing ansible with following parameters:"
            echo "ansible-playbook -i ${install}-${environment} ${playbook} --user=${ansible_user} --ask-pass --ask-vault-pass"
            sleep 5

            ansible-playbook -i ${install}-${environment} ${playbook}  \
	             --user=${ansible_user} --ask-pass --ask-vault-pass
        ;;         
    esac

    
fi
