#encoding "utf8"

DateS -> 'когда'Colon | 'коли'Colon | 'дата проведения' | 'Время' | 'января' | 'февраля' | 'марта' | 'апреля' | 'мая' | 'июня' | 'июля' | 'августа' | 'сентября' | 'октября' | 'ноября' | 'декабря';

Starting -> 'начало' | 'початок' | 'старт' | 'початок' | 'начинается';

DateSS -> DateS;

DateOnlyDays -> (AnyWord<wff=/[0-3][0-9]-[0-3][0-9]/>) Word;

DateMain -> (Word) (AnyWord<wff=/[0-3][0-9]/>) Word;

DateTimeOnly -> DateMain ("время") (Colon) (Word) ("з") ("с") AnyWord<wff=/[0-2][0-9]:[0-5][0-9]/> ("по") ("до") (Hyphen) (Word) AnyWord<wff=/[0-2][0-9]:[0-5][0-9]/>;

DateToDT -> Word ("сегодня") ("завтра") ("послезавтра") (Comma) (AnyWord<wff=/[0-2][0-9]:[0-6][0-9]/>);

DateStupidForm -> (Word) (Comma) Starting (Prep) (AnyWord<wff=/[0-2][0-9]:[0-5][0-9]/>) (Word);

DateMainT ->  DateOnlyDays | DateMain | DateTimeOnly | DateToDT | DateStupidForm;

DateSM -> DateSS;

Date -> DateSM interp (Date.prey) DateMainT interp (Date.Data);