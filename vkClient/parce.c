#include <stdio.h>
#include <stdlib.h>
#include "cJSON.h"
#include <stdio.h>
#include "parce.h"

void parse(char * text )
{
cJSON * jList = cJSON_Parse(text);
	if (!jList) {
		printf("Error before: [%s]\n", cJSON_GetErrorPtr());
		return 1;
	}
cJSON * jItem = cJSON_GetArrayItem(jList, 0);
 //int count = cJSON_GetArraySize(jItem);
 //printf("%d",count);
 cJSON * jArr = cJSON_GetArrayItem(jItem, 1);
 char * t = cJSON_GetObjectItem(jArr, "text")->valuestring;
   FILE *fp;

if ((fp = fopen("test", "w"))==NULL) {
  printf("He удается открыть файл.\n");
  exit(1);
}
fwrite(t,1,strlen(t),fp);



}
