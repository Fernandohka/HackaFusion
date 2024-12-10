# FRONT-BACK
---
## **AUTH:**

###    POST/auth
        {	
            "login"		: string,
            "password"	: string
        } 
#### return
            {
                "admin"		: boolean,
                "token"		: string,
                "message"	: string
            }

---
## **USER:**

###    POST/user
        {
            "name"		: string,
            "email"		: string,
            "EDV"		: string,
            "phone"		: string,
            "password"	: string
        }
#### return
            {
                "mesage"	: string
            }
    
------
### GET/user?page=''&size=''&query=''
#### return
        {
            "numPage"		: int,
            "listObject"	:
            [
                {
                    "id"		: int,
                    "name"		: string,
                    "image"		: string,
                    "admin"		: boolean
                },
                {}
            ]
        }
    
------
###     GET/user/{id}
####    return
        {
            "id"		: int,
            "name"		: string,
            "email"		: string,
            "EDV"		: string,
            "phone"		: string,
            "student"	: boolean
        }
    
------
###    PUT/user/password
        {
            "password"		: string,
            "newPassword"	: string
        }
#### return
            {
                "mesage"	: string
            }
        
------
###    PUT/user
        {
            "name"		: string,
            "image"		: string,
            "email"		: string,
            "EDV"		: string,
            "phone"		: string,
            "student"	: boolean
        }
#### return
            {
                "message"	: string
            }

------
###    DELETE/user/{id}
#### return
        {
            "message"	: string
        }
			
---		
## **FORUM:**

###    GET/forum/{id}
#### return
        {
            "id"			: int,
            "name"			: string,
            "description"	: string
        }

------
###    GET/forum?page=''&size=''&query=''
#### return
            {
                "numPage"		: int,
                "listObject"	:
                [
                    {
                        "id"			: int,	
                        "name"			: string,
                        "description"	: string
                    },
                    {}
                ]
            }
    

------
###    POST/admin/forum
        {
            "name"			: string,
            "description"	: string
        }
#### return
            {
                "message"	: string
            }

------
###    DELETE/admin/forum/{id}
#### return
        {
            "message"	: string
        }

---
## **CHAT:**
###    POST/chat/user/{id}
#### return
            {
                "message"	: string
            }

------
###    GET/chat?query=''
#### return
        {
            "numPage"		: int,
            "listObject"	:
            [
                "id"	: int,	
                "user"	:
                {
                    "id"        : string,
                    "name"      : string,
                    "edv"       : string,
                    "email"     : string,
                    "telefone"  : string,
                    "image"     : string
                },
                {}
            ]
        }

------
###    GET/chat/{id}
#### return
        {
            "numPage"       : int,
            "listObject"    :
            [
                {
                    "id"			: int,
                    "description"	: string,
                    "timestamp"		: date,
                    "user"			:
                    {
                        "id"        : string,
                        "name"      : string,
                        "edv"       : string,
                        "email"     : string,
                        "telefone"  : string,
                        "image"     : string
                    }
                },
                {}
            ]
        }

------
###    POST/chat/message
        {
            "description"	: string,
            "chatId"		: int
        }
#### return
        {
            "message"       : string
        }

---
## **PROJECTS:**
###    POST/project
        {
            "name"			: string,
            "description"	: string,
            "status"		: boolean,
            "startDate"		: date,
            "endDate"		: date,
            "idCategory"	: int
        }
#### return
            {
                "message"	: string
            }

------
###    GET/project?page=''&size=''&query=''
#### return
        {
            [
                {
                    "id"			: int,
                    "name"			: string,
                    "description"	: string
                },
                {}
            ]
        }

------
###     GET/project/{id}
#### return
        {
            "id"            : int,
            "name"          : string,
            "description"   : string,
            "status"        : booelan,
            "startDate"     : date,
            "endDate"       : date,
            "category"      : string
        }
    
------
###    POST/project/feedback
        {
            "isPrivate"     : boolean,
            "idProject"		: int,
            "description	: string
        }
#### return
        {
            "message"   : string
        }
------
###     GET/project/{idProject}/feedback/{îdUser}
####    return
        {
            "numPage"       : int,
            "listObject"    :
            [
                {
                    "id",
                    "description",
                    "isPrivate",
                    "user" :
                    {
                        "id"        : string,
                        "name"      : string,
                        "edv"       : string,
                        "email"     : string,
                        "telefone"  : string,
                        "image"     : string
                    }
                },
                {}
            ]
        }

------
###     GET/project/{idProject}/feedback?page=''&size=''&query=''
####    return
        {
            "numPage"       : int,
            "listObject"    :
            [
                {
                    "id",
                    "description",
                    "isPrivate",
                    "user" :
                    {
                        "id"        : string,
                        "name"      : string,
                        "edv"       : string,
                        "email"     : string,
                        "telefone"  : string,
                        "image"     : string
                    }
                },
                {}
            ]
        }
    
------
###    POST/project/user
        {
            "idProject"	: int
            "idUser"	: int
        }
#### return
        {
            "message"       : string
        }
    
------
###    DELETE/project/user
        {
            "idProject"	: int
            "idUser"	: int
        }
#### return
            {
                "message"	: string 
            }
------
###     GET/project/user/{id}
####    return
        {
            "id"            : int,
            "name"          : string,
            "description"   : string,
            "status"        : booelan,
            "startDate"     : date,
            "endDate"       : date,
            category"       : string
        }
------
###     POST/project/message
        {
            "idProject"     : int,
            "description"   : string,
            "timestamp"     : date
        }
#### return
    {
        "message"   : string
    }
------
###     GET/project/{id}/message?page=''&size=''
####    return
        {
            "numPage"       : int
            "listObject"    :
            [
                {
                    "id"            : int,
                    "description"   : description,
                    "timestamp"     : date,
                    "user"          :
                    {
                        "id"        : string
                        "name"      : string
                        "edv"       : string
                        "email"     : string
                        "telefone"  : string
                        "image"     : string
                    }
                }
            ]
        }

        
## **:**

		

		



		



		
			












