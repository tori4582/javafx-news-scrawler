package com.fxnews.repository;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Data
public class ProviderRepository {

    private static ProviderRepository INSTANCE;
    private Map<String, Repository> providers;

    public static final ProviderRepository getInstance() {
        if (ProviderRepository.INSTANCE == null) {
            INSTANCE = new ProviderRepository();
        }

        return ProviderRepository.INSTANCE;
    }

    private ProviderRepository() {
        this.providers = Map.of(
                "VNEXPRESS", new VnExpressRepository(),
                "TUOITRE", new TuoiTreRepository(),
                "NHANDAN", new NhanDanRepository(),
                "ZING", new ZingNewsRepository(),
                "THANHNIEN", new ThanhNienRepository()
        );
    }


    public Repository getProvider(String provider) {
        return providers.get(provider);
    }

    public List<String> getProviders() {
        return new ArrayList<>(providers.keySet());
    }

}
