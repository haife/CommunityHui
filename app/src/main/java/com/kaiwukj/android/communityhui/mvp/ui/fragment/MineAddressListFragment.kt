package com.kaiwukj.android.communityhui.mvp.ui.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.haife.app.nobles.spirits.kotlin.mvp.ui.decoration.RecycleViewDivide
import com.kaiwukj.android.communityhui.R
import com.kaiwukj.android.communityhui.app.base.BaseSwipeBackFragment
import com.kaiwukj.android.communityhui.app.constant.ExtraCons
import com.kaiwukj.android.communityhui.di.component.DaggerEditMineInfoComponent
import com.kaiwukj.android.communityhui.di.module.EditMineInfoModule
import com.kaiwukj.android.communityhui.mvp.contract.EditMineInfoContract
import com.kaiwukj.android.communityhui.mvp.http.entity.request.MineCollectionResult
import com.kaiwukj.android.communityhui.mvp.http.entity.result.MyAddressResult
import com.kaiwukj.android.communityhui.mvp.presenter.EditMineInfoPresenter
import com.kaiwukj.android.communityhui.mvp.ui.adapter.MyAddressAdapter
import com.kaiwukj.android.mcas.di.component.AppComponent
import com.qmuiteam.qmui.widget.QMUITopBar
import kotlinx.android.synthetic.main.fragment_mine_address_list.*
import me.yokeyword.fragmentation.ISupportFragment

/**
 * Copyright © KaiWu Technology Company
 * @author Haife
 * @job Android Development
 * @company KW | 开物科技
 * @time 2019/7/17
 * @desc  我的地址
 */
class MineAddressListFragment : BaseSwipeBackFragment<EditMineInfoPresenter>(), EditMineInfoContract.View {

    lateinit var mAddressAdapter: MyAddressAdapter
    lateinit var mAddressList: ArrayList<MyAddressResult>
    var isChoiceAddress: Boolean = false

    companion object {
        const val MINE_ADDRESS_LIST_FRAGMENT = "MINE_ADDRESS_LIST_FRAGMENT"
        const val REQUEST_CODE_EDIT_ADDRESS = 1
        //判断进入这个的场景是否是选择地址
        fun newInstance(choiceAddress: Boolean): MineAddressListFragment {
            val fragment = MineAddressListFragment()
            fragment.isChoiceAddress = choiceAddress
            return fragment
        }
    }


    override fun setupFragmentComponent(appComponent: AppComponent) {
        DaggerEditMineInfoComponent
                .builder()
                .appComponent(appComponent)
                .editMineInfoModule(EditMineInfoModule(this))
                .build()
                .inject(this)
    }

    override fun initView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.fragment_mine_address_list, container, false);
    }

    override fun initData(savedInstanceState: Bundle?) {
        mPresenter?.requestMyAddress()
        rv_mine_address_list.layoutManager = LinearLayoutManager(context)
        rv_mine_address_list.addItemDecoration(RecycleViewDivide(drawableId = null, divideHeight = 20,
                divideColor = ContextCompat.getColor(context!!, R.color.window_background_color)))
        mAddressAdapter = MyAddressAdapter(R.layout.recycle_item_mine_address_list, mAddressList, context!!)
        rv_mine_address_list.adapter = mAddressAdapter


        mAddressAdapter.setOnItemChildClickListener { adapter, view, position ->
            when (view.id) {
                R.id.iv_mine_address_edit -> {
                    startForResult(EditMineAddressFragment.newInstance(mAddressList[position]), REQUEST_CODE_EDIT_ADDRESS)
                }
            }
        }

        mAddressAdapter.setOnItemClickListener { adapter, view, position ->
            if (isChoiceAddress) {
                val bundle = Bundle()
                bundle.putSerializable(ExtraCons.EXTRA_KEY_CHOICE_ADDRESS, mAddressList[position])
                setFragmentResult(ISupportFragment.RESULT_OK, bundle)
                killMyself()
            }
        }

    }

    override fun onFragmentResult(requestCode: Int, resultCode: Int, data: Bundle?) {
        super.onFragmentResult(requestCode, resultCode, data)

    }

    override fun onGetMyCollectionData(list: List<MineCollectionResult>) {
    }

    override fun onGetMyAddressList(result: MyAddressResult) {
        mAddressList.addAll(result.result)
        mAddressAdapter.notifyDataSetChanged()
    }

    override fun post(runnable: Runnable?) {
    }

    override fun onSupportVisible() {
        super.onSupportVisible()
        activity?.findViewById<QMUITopBar>(R.id.qtb_edit_mine_info)?.setTitle(getString(R.string.mine_address_title))

    }

    override fun showLoading() {

    }

    override fun hideLoading() {

    }

    override fun showMessage(message: String) {
    }

    override fun launchActivity(intent: Intent) {
    }

    override fun killMyself() {
        activity?.onBackPressed()
    }
}
