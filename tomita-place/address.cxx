#encoding "utf8"

StreetW -> 'проспект' | 'улица' | 'бульвар' | 'переулок' | 'парк' | 'где' | 'Где' | 'Киев';

StreetSokr -> 'пр' | 'просп' | 'пр-д' | 'ул' | 'ул' | 'пер' ;

PlaceT -> StreetW | StreetSokr;

PlaceTW -> PlaceT+ (Punct) Word;

PlaceTWW -> PlaceT+ (Punct) Word (Comma) (Word) (Comma) (AnyWord<wff=/[1-9]?[0-9]?/>);

PlaceTWCA -> PlaceT+ Word PlaceT* (Comma) PlaceT* (Word);

PlaceMazo -> PlaceT PlaceT Punct* Word Comma* Punct AnyWord<wff=/[1-9]?[0-9]?/>;

PlaceTWA -> PlaceT+ Word PlaceT* (Word) PlaceT* AnyWord<wff=/[1-9]?[0-9]?/>;

PlaceMain ->  PlaceTW | PlaceTWW | PlaceTWCA | PlaceTWA | PlaceMazo;

Place -> PlaceMain interp (Address.StreetName);