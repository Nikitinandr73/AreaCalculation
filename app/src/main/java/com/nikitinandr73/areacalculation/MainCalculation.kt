package com.nikitinandr73.areacalculation

import android.annotation.SuppressLint
import android.util.Log
import android.util.Log.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nikitinandr73.areacalculation.ui.theme.Cmania1
import com.nikitinandr73.areacalculation.ui.theme.Cmania2
import com.nikitinandr73.areacalculation.ui.theme.LaytBG
import com.nikitinandr73.areacalculation.ui.theme.LaytBG2
import kotlin.math.PI

@SuppressLint("StateFlowValueCalledInComposition")
@Preview(showBackground = true, backgroundColor = 0)
@Composable
fun MainCalculation() {
    val summNaruzhPlosk = remember { mutableFloatStateOf(0f) }
    val summVnutrPlosk = remember { mutableFloatStateOf(0f) }
    val summGlobal = remember { mutableFloatStateOf(0f) }
    var sum_nar_pl = 0f
    var sum_vn_pl = 0f
    var sum_torc_pl: Float
    var max_diam_detali: Float
    var min_diam_vnenr_otv: Float
    var global_summ = 0f
    val sgmm = summGlobal.value
    val sgdm = summGlobal.value / 10000

    var MaxDiametrDetali = 0

    val KolvoNaruznihPloskostey = remember {
        mutableStateOf("")
    }
    val SummaNaruznihPloskostey = remember {
        mutableStateOf("")
    }
    val KolvoVnutrennihPloskostey = remember {
        mutableStateOf("")
    }
    val SummaVnutrennihPloskostey = remember {
        mutableStateOf("")
    }
    var MinDiametrVnutrennihPloskostey: Int = 0

    val SummaTorcevihihPloskostey = remember {
        mutableStateOf("")
    }
//создаем лист наружних диаметров

    var array_list_nar_Diam = mutableListOf<Int>()

// создаем лист внутренних диаметров

    var array_list_vnutr_Diam = mutableListOf<Int>()


    //функция добавляет наружние диаметры в лист наружних диаметров
    fun addItem_list_nar_diam(x: Int) {
        array_list_nar_Diam.add(x)
    }

    // функция добавляет внутринние диаметры в лист внутренних диаметров
    fun addItem_list_vnutr_diam(x: Int) {
        array_list_vnutr_Diam.add(x)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
// Карточка поля ввода количества наружних плоскостей
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = Cmania1
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 20.dp
            ),
            shape = RoundedCornerShape(10.dp)
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                value = KolvoNaruznihPloskostey.value,
                onValueChange = { KolvoNaruznihPloskostey.value = it },
                label = {
                    Text(
                        stringResource(id = R.string.K_n_p), fontSize = 20.sp,
                        color = LaytBG
                    )
                },
                trailingIcon = {

                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                textStyle = TextStyle(fontSize = 20.sp, color = LaytBG2),
                shape = RoundedCornerShape(10.dp)

            )
            val knp: Int = KolvoNaruznihPloskostey.value.toIntOrNull() ?: 0

// В зависимости сколько вн.плоскостей - столько и рисуем окон
// Добавляем  наружные плоскости:
            if (knp > 0) {
                (1..knp).forEach() { i ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Cmania1
                        ),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 20.dp
                        ),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            val VisotaDetali = remember {
                                mutableStateOf("")
                            }
                            val DiametrDetali = remember {
                                mutableStateOf("")
                            }
                            val VisotaRezbi = remember {
                                mutableStateOf("")
                            }
                            Row(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .padding(10.dp),
                                horizontalArrangement = Arrangement.Center
                            )
                            {
                                Text(
                                    // "Редактируем"
                                    text = stringResource(id = R.string.Redaktiruem),
                                    fontSize = 18.sp,
                                    color = Color.White
                                )
                                Text(
                                    "$i",
                                    fontSize = 18.sp,
                                    color = Color.White
                                )
                                Text(
                                    //наружнюю плоскость "
                                    text = stringResource(id = R.string.n_p),
                                    fontSize = 18.sp,
                                    color = Color.White
                                )
                            }

                            OutlinedTextField(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(5.dp),
                                value = VisotaDetali.value,
                                onValueChange = { VisotaDetali.value = it },
                                label = {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxHeight()
                                            .padding(10.dp),
                                        horizontalArrangement = Arrangement.Start
                                    )
                                    {
                                        Text(
                                            // "Высота"
                                            text = stringResource(id = R.string.visota),
                                            fontSize = 20.sp,
                                            color = LaytBG
                                        )
                                        Text(
                                            "$i",
                                            fontSize = 20.sp,
                                            color = LaytBG
                                        )
                                        Text(
                                            //плоскости
                                            text = stringResource(id = R.string.ploskosti),
                                            fontSize = 20.sp,
                                            color = LaytBG
                                        )
                                    }
                                },
                                trailingIcon = {
                                    Text(
                                        text = stringResource(id = R.string.mm),
                                        fontSize = 20.sp,
                                        color = LaytBG
                                    )
                                },
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Number
                                ),
                                singleLine = true,
                                textStyle = TextStyle(
                                    fontSize = 20.sp,
                                    color = LaytBG2
                                ),
                                shape = RoundedCornerShape(10.dp)
                            )
                            OutlinedTextField(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(5.dp),
                                value = DiametrDetali.value,
                                onValueChange = { DiametrDetali.value = it },
                                label = {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxHeight()
                                            .padding(10.dp),
                                        horizontalArrangement = Arrangement.Start
                                    )
                                    {
                                        Text(
                                            // "Диаметр"
                                            text = stringResource(id = R.string.diametr),
                                            fontSize = 20.sp,
                                            color = LaytBG
                                        )
                                        Text(
                                            "$i",
                                            fontSize = 20.sp,
                                            color = LaytBG
                                        )
                                        Text(
                                            //плоскости
                                            text = stringResource(id = R.string.ploskosti),
                                            fontSize = 20.sp,
                                            color = LaytBG
                                        )
                                    }
                                },
                                trailingIcon = {
                                    Text(
                                        text = stringResource(id = R.string.mm),
                                        fontSize = 20.sp,
                                        color = LaytBG
                                    )
                                },
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Number
                                ),
                                singleLine = true,
                                textStyle = TextStyle(
                                    fontSize = 20.sp,
                                    color = LaytBG2
                                ),
                                shape = RoundedCornerShape(10.dp)
                            )

                            OutlinedTextField(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(5.dp),
                                value = VisotaRezbi.value,
                                onValueChange = { VisotaRezbi.value = it },
                                label = {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxHeight()
                                            .padding(10.dp),
                                        horizontalArrangement = Arrangement.Start
                                    )
                                    {
                                        Text(
                                            // "Высота резьбы на"
                                            text = stringResource(id = R.string.visota_rezbi_na),
                                            fontSize = 20.sp,
                                            color = LaytBG
                                        )
                                        Text(
                                            "$i",
                                            fontSize = 20.sp,
                                            color = LaytBG
                                        )
                                        Text(
                                            //плоскости
                                            text = stringResource(id = R.string.ploskosti),
                                            fontSize = 20.sp,
                                            color = LaytBG
                                        )
                                    }
                                },
                                trailingIcon = {
                                    Text(
                                        text = stringResource(id = R.string.mm),
                                        fontSize = 20.sp,
                                        color = LaytBG
                                    )
                                },
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Number
                                ),
                                singleLine = true,
                                textStyle = TextStyle(
                                    fontSize = 20.sp,
                                    color = LaytBG2
                                ),
                                shape = RoundedCornerShape(10.dp)
                            )
//добавляем в лист наружних диаметров

                            val narpl = remember { mutableStateOf(0f) }
                            val vis = VisotaDetali.value.toFloatOrNull() ?: 0f
                            val diam = DiametrDetali.value.toFloatOrNull() ?: 0f
                            val schetResult = PI * vis * diam
                            val vis_rezbi = VisotaRezbi.value.toFloatOrNull() ?: 0f
                            val plos_rezbi = PI * vis_rezbi * diam * 0.7
                            narpl.value =
                                schetResult.toFloat() + plos_rezbi.toFloat()  // Площадь I-ой плоскости
                            val sum_nar_pl_i = summNaruzhPlosk.floatValue + narpl.value
                            sum_nar_pl += sum_nar_pl_i
// Глобальная сумма наружних плоскостей:
                            SummaNaruznihPloskostey.value = sum_nar_pl.toString()
// в цикле наполним множество  наружними диаметрами:
                            addItem_list_nar_diam(x = diam.toInt())

                            Row(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .padding(10.dp),
                                horizontalArrangement = Arrangement.Start
                            )
                            {
                                Text(
                                    // "Площадь плоскости "
                                    text = stringResource(
                                        id = R.string.Plosh_ploskosti
                                    ),
                                    color = Color.White
                                )
                                Text(
                                    "${narpl.value}",
                                    color = Color.White
                                )
                                Text(
                                    //мм.кв
                                    text = stringResource(id = R.string.mm_kv),
                                    color = Color.White
                                )
                            }
                        }

                    }
                }
            }
        }
        fun poisk_max(x: MutableList<Int>) {
            for (i in x) {
                if (i > MaxDiametrDetali) {
                    MaxDiametrDetali = i
                }
            }
        }
        poisk_max(array_list_nar_Diam)
// Карточка поля ввода количества внутренних плоскостей
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = Cmania1
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 20.dp
            ),
            shape = RoundedCornerShape(10.dp)
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                value = KolvoVnutrennihPloskostey.value,
                onValueChange = { KolvoVnutrennihPloskostey.value = it },
                label = {

//              "Кол-во внутренних плоскостей"
                    Text(
                        stringResource(id = R.string.K_v_p), fontSize = 20.sp,
                        color = LaytBG
                    )
                },
                trailingIcon = {
                },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                textStyle = TextStyle(fontSize = 20.sp, color = LaytBG2),
                shape = RoundedCornerShape(10.dp)

            )
            val kvp: Int = KolvoVnutrennihPloskostey.value.toIntOrNull() ?: 0
            if (kvp > 0) {

// В зависимости сколько вн.плоскостей - столько и рисуем окон


                (1..kvp).forEach() { i ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Cmania1
                        ),
                        elevation = CardDefaults.cardElevation(
                            defaultElevation = 40.dp
                        ),
                        shape = RoundedCornerShape(10.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            val VisotaVnutrPlosk = remember {
                                mutableStateOf("")
                            }
                            val DiametrVnutrPlosk = remember {
                                mutableStateOf("")
                            }
                            val VisotaRezbiVnutrPlosk = remember {
                                mutableStateOf("")
                            }

//  text = "Редактируем $i внутреннюю плоскость ",

                            Row(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .padding(10.dp),
                                horizontalArrangement = Arrangement.Center
                            )
                            {
                                Text(
                                    // "Редактируем"
                                    text = stringResource(id = R.string.Redaktiruem),
                                    fontSize = 18.sp,
                                    color = Color.White
                                )
                                Text(
                                    "$i",
                                    fontSize = 18.sp,
                                    color = Color.White
                                )
                                Text(
                                    //внутреннюю плоскость "
                                    text = stringResource(id = R.string.v_p),
                                    fontSize = 18.sp,
                                    color = Color.White
                                )
                            }
                            OutlinedTextField(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(5.dp),
                                value = VisotaVnutrPlosk.value,
                                onValueChange = { VisotaVnutrPlosk.value = it },
                                label = {
//                                    Text(
//                                        "Высота $i плоскости", fontSize = 20.sp,
//                                        color = LaytBG
//                                    )
                                    Row(
                                        modifier = Modifier
                                            .fillMaxHeight()
                                            .padding(10.dp),
                                        horizontalArrangement = Arrangement.Start
                                    )
                                    {
                                        Text(
                                            // "Высота"
                                            text = stringResource(id = R.string.visota),
                                            fontSize = 20.sp,
                                            color = LaytBG
                                        )
                                        Text(
                                            "$i",
                                            fontSize = 20.sp,
                                            color = LaytBG
                                        )
                                        Text(
                                            //плоскости
                                            text = stringResource(id = R.string.ploskosti),
                                            fontSize = 20.sp,
                                            color = LaytBG
                                        )
                                    }
                                },
                                trailingIcon = {
                                    Text(
                                        text = stringResource(id = R.string.mm),
                                        fontSize = 20.sp,
                                        color = LaytBG
                                    )
                                },
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Number
                                ),
                                singleLine = true,
                                textStyle = TextStyle(
                                    fontSize = 20.sp,
                                    color = LaytBG2
                                ),
                                shape = RoundedCornerShape(10.dp)
                            )
                            OutlinedTextField(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(5.dp),
                                value = DiametrVnutrPlosk.value,
                                onValueChange = { DiametrVnutrPlosk.value = it },
                                label = {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxHeight()
                                            .padding(10.dp),
                                        horizontalArrangement = Arrangement.Start
                                    )
                                    {
                                        Text(
                                            // "Диаметр"
                                            text = stringResource(id = R.string.diametr),
                                            fontSize = 20.sp,
                                            color = LaytBG
                                        )
                                        Text(
                                            "$i",
                                            fontSize = 20.sp,
                                            color = LaytBG
                                        )
                                        Text(
                                            //плоскости
                                            text = stringResource(id = R.string.ploskosti),
                                            fontSize = 20.sp,
                                            color = LaytBG
                                        )
                                    }
                                },
                                trailingIcon = {
                                    Text(
                                        text = stringResource(id = R.string.mm),
                                        fontSize = 20.sp,
                                        color = LaytBG
                                    )
                                },
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Number
                                ),
                                singleLine = true,
                                textStyle = TextStyle(
                                    fontSize = 20.sp,
                                    color = LaytBG2
                                ),
                                shape = RoundedCornerShape(10.dp)
                            )
                            OutlinedTextField(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(5.dp),
                                value = VisotaRezbiVnutrPlosk.value,
                                onValueChange = { VisotaRezbiVnutrPlosk.value = it },
                                label = {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxHeight()
                                            .padding(10.dp),
                                        horizontalArrangement = Arrangement.Start
                                    )
                                    {
                                        Text(
                                            // "Высота резьбы на"
                                            text = stringResource(id = R.string.visota_rezbi_na),
                                            fontSize = 20.sp,
                                            color = LaytBG
                                        )
                                        Text(
                                            "$i",
                                            fontSize = 20.sp,
                                            color = LaytBG
                                        )
                                        Text(
                                            //плоскости
                                            text = stringResource(id = R.string.ploskosti),
                                            fontSize = 20.sp,
                                            color = LaytBG
                                        )
                                    }
                                },
                                trailingIcon = {
                                    Text(
                                        text = stringResource(id = R.string.mm),
                                        fontSize = 20.sp,
                                        color = LaytBG
                                    )
                                },
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = KeyboardType.Number
                                ),
                                singleLine = true,
                                textStyle = TextStyle(
                                    fontSize = 20.sp,
                                    color = LaytBG2
                                ),
                                shape = RoundedCornerShape(10.dp)
                            )

                            val vnpl = remember { mutableStateOf(0f) }
                            val vis_vp = VisotaVnutrPlosk.value.toFloatOrNull() ?: 0f
                            val diam_vp = DiametrVnutrPlosk.value.toFloatOrNull() ?: 0f
                            val schetResult = PI * vis_vp * diam_vp
                            val vis_rezbi_vp = VisotaRezbiVnutrPlosk.value.toFloatOrNull() ?: 0f
                            val plos_rezbi = PI * vis_rezbi_vp * diam_vp * 0.7
                            vnpl.value =
                                schetResult.toFloat() + plos_rezbi.toFloat()  // Площадь I-ой плоскости
                            val sum_vn_pl_i = summVnutrPlosk.floatValue + vnpl.value
                            sum_vn_pl += sum_vn_pl_i
//Глобальная сумма внутренних плоскостей:
                            SummaVnutrennihPloskostey.value = sum_vn_pl.toString()
//в цикле наполним множество  наружними диаметрами:
                            addItem_list_vnutr_diam(x = diam_vp.toInt())

                            Row(
                                modifier = Modifier
                                    .fillMaxHeight()
                                    .padding(10.dp),
                                horizontalArrangement = Arrangement.Start
                            )
                            {
                                Text(
                                    // "Площадь плоскости "
                                    text = stringResource(id = R.string.Plosh_ploskosti),
                                    color = Color.White
                                )
                                Text(
                                    "${vnpl.value}",
                                    color = Color.White
                                )
                                Text(
                                    //мм.кв
                                    text = stringResource(id = R.string.mm_kv),
                                    color = Color.White
                                )
                            }
                            fun poisk_min(x: MutableList<Int>) {
                                MinDiametrVnutrennihPloskostey = x[0]
                                for (i in x) {
                                    if (i <= MinDiametrVnutrennihPloskostey) {
                                        MinDiametrVnutrennihPloskostey = i
                                    }
                                }
                            }
                            poisk_max(array_list_nar_Diam)
                            poisk_min(array_list_vnutr_Diam)
                        }

                    }

                }
            }
        }
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(
                containerColor = Cmania1
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 20.dp
            ),
            shape = RoundedCornerShape(10.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp),
                verticalArrangement = Arrangement.SpaceEvenly,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = stringResource(id = R.string.S_p_p),
                    fontSize = 16.sp,
                    color = LaytBG
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
// Наружние
                    Text(
                        text = stringResource(id = R.string.Naruzhnie),
                        color = LaytBG
                    )
                    Text(
                        SummaNaruznihPloskostey.value,
                        color = LaytBG
                    )
                    Text(
                        text = stringResource(id = R.string.mm_kv),
                        color = LaytBG
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(id = R.string.Vnutrennie),
                        color = LaytBG
                    )
                    Text(
                        SummaVnutrennihPloskostey.value,
                        color = LaytBG
                    )
                    Text(
                        text = stringResource(id = R.string.mm_kv),
                        color = LaytBG
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = stringResource(id = R.string.Torcevie),
                        color = LaytBG
                    )
                    Text(
                        SummaTorcevihihPloskostey.value,
                        color = LaytBG
                    )
                    Text(
                        text = stringResource(id = R.string.mm_kv),
                        color = LaytBG
                    )
                }
            }
        }
        max_diam_detali = MaxDiametrDetali.toFloat()
        min_diam_vnenr_otv = MinDiametrVnutrennihPloskostey.toFloat()
        sum_torc_pl = (((max_diam_detali * max_diam_detali / 4 * PI) -
                (min_diam_vnenr_otv * min_diam_vnenr_otv / 4 * PI)) * 2).toFloat()
        SummaTorcevihihPloskostey.value = sum_torc_pl.toString()
        SummaNaruznihPloskostey.value = sum_nar_pl.toString()
        SummaVnutrennihPloskostey.value = sum_vn_pl.toString()

        Button(
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Cmania2, contentColor = LaytBG2
            ),
            onClick = {
                global_summ = sum_nar_pl + sum_vn_pl + sum_torc_pl
                summGlobal.floatValue = global_summ

            })
        {
// Посчитать общую площадь
            Text(
                text = stringResource(id = R.string.P_O_P),
                fontSize = 20.sp
            )
        }
//// Todo проверка начало
//        Text(
//            text = """
//                |SummaNaruznihPloskostey =${SummaNaruznihPloskostey.value}
//                |SummaVnutrennihPloskostey =${SummaVnutrennihPloskostey.value}
//                |SummaTorcevihihPloskostey = ${SummaTorcevihihPloskostey.value}
//            """.trimMargin(),
//            color = LaytBG
//        )
//// Todo проверка конец

// // Колонка с Результататоми рассчетов
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        )
        {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            )
            {
                Text(
                    // "Площадь плоскости мм "
                    text = stringResource(id = R.string.Total_ploshch),
                    color = Color.White
                )
                Text(
                    "$sgmm",
                    fontSize = 24.sp,
                    color = Color.White
                )
                Text(
                    //мм.кв
                    text = stringResource(id = R.string.mm_kv),
                    color = Color.White
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            )
            {
                Text(
// "Площадь плоскости"
                    text = stringResource(id = R.string.Total_ploshch),
                    color = Color.White
                )
                Text(
                    "$sgdm",
                    fontSize = 24.sp,
                    color = Color.White
                )
                Text(
                    //дм.кв
                    text = stringResource(id = R.string.dm_kv),
                    color = Color.White
                )
            }
        }
    }

    Row() {
        Text(text = "${SummaNaruznihPloskostey.value}",
            fontSize = 1.sp)
        Text(text = "${SummaVnutrennihPloskostey.value}",
            fontSize = 1.sp)
        Text(text = "${SummaTorcevihihPloskostey.value}",
            fontSize = 1.sp)

    }

}



