#encoding "utf8"

PriceW -> 'стоимость'Colon | 'стоимость' | 'вартість'Colon | 'вартість' | 'стоит' | 'вход' | 'вход'Colon;

PriceMV -> 'гривень' | 'гривні' | 'гривен' | 'грн';

PriceFR -> 'бесплатно' | 'халява' | 'нет оплаты' | 'свободный';

PriceS -> PriceW (Colon);

PriceDich -> Word* AnyWord* PriceMV;

PriceD -> Word* AnyWord<wff=/[0-9][0-9]?[0-9]?[0-9]?/>  (PriceMV) Hyphen AnyWord<wff=/[0-9][0-9]?[0-9]?[0-9]?/> PriceMV;

PriceNew -> Word* AnyWord<wff=/[0-9][0-9]?[0-9]?[0-9]?/> (PriceMV);

PriceMazo -> Word AnyWord<wff=/[0-9][0-9]?[0-9]?[0-9]?/>* PriceMV*;

PriceFree -> PriceFR;

PriceF -> PriceD;

PriceFinal -> PriceF | PriceNew | PriceMazo | PriceFree | PriceDich;

Price -> PriceS interp (Price.val) PriceFinal interp (Price.Price);